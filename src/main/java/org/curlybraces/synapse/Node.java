package org.curlybraces.synapse;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

public class Node
{
    private final Logger logger = LoggerFactory.getLogger(Node.class);
    
    public final UUID MIN_UUID = new UUID(Long.MIN_VALUE, Long.MIN_VALUE);
    
    public final UUID MAX_UUID = new UUID(Long.MAX_VALUE, Long.MAX_VALUE);
    
    private final UUID id;

    private final Dictionary dictionary;

    private final ArchiveManager archiveManager;

    private final List<SynapseListener> listOfListeners;

    private final Map<UUID, Runnable> callbacks;

    private final LinkedList<UUID> calledback;

    private final LinkedBlockingQueue<Task> envelopes;

    /** The message network. */
    private final Network<UUID> messageNetwork;
    
    /** The message storage. */
    private final Storage<Message> messageStorage;
    
    private final Network<UUID> profileNetwork;
    
    private final Storage<Profile> profileStorage;

    private Thread mailman;

    private URL url;

    @Inject
    public Node(Dictionary siloManager, ArchiveManager archiveManager)
    {
        UUID id = UUID.randomUUID();
        
        Storage<Message> messageStorage = new Storage<Message>();
        messageStorage.create(MIN_UUID, MAX_UUID);

        Route route = new Route(id, true);
        Network<UUID> messageNetwork = new Network<UUID>(UUID.randomUUID(), MIN_UUID);
        messageNetwork.get(messageNetwork.getRootId()).add(MIN_UUID, route);
        
        Storage<Profile> profileStorage = new Storage<Profile>();
        profileStorage.create(MIN_UUID, MAX_UUID);
        
        route = new Route(id, true);
        Network<UUID> profileNetwork = new Network<UUID>(UUID.randomUUID(), MIN_UUID);
        profileNetwork.get(profileNetwork.getRootId()).add(MIN_UUID, route);

        this.id = id;
        this.dictionary = siloManager;
        this.archiveManager = archiveManager;
        this.listOfListeners = new ArrayList<SynapseListener>();
        this.callbacks = new HashMap<UUID, Runnable>();
        this.calledback = new LinkedList<UUID>();
        this.envelopes = new LinkedBlockingQueue<Task>();

        this.messageNetwork = messageNetwork;
        this.messageStorage = messageStorage;
        
        this.profileNetwork = profileNetwork;
        this.profileStorage = profileStorage;
    }

    public void start()
    {
        mailman = new Thread(new Runnable()
        {
            public void run()
            {
                logger.debug("Starting mailman thread.");
                
                while (actuallySendCommand())
                {
                }

                logger.debug("Stopping mailman thread.");
            }
        });
        mailman.start();
    }
    
    public void stop()
    {
        try
        {
            envelopes.put(new TerminalTask());
        }
        catch (InterruptedException e)
        {
        }
    }
    
    public void join() throws InterruptedException
    {
        mailman.join();
    }

    public UUID getId()
    {
        return id;
    }

    public void setURL(URL url)
    {
        this.url = url;
        getMessageNetwork().get(getMessageNetwork().getRootId()).get(MIN_UUID).add(url);
        getProfileNetwork().get(getProfileNetwork().getRootId()).get(MIN_UUID).add(url);
    }

    public URL getURL()
    {
        return url;
    }

    public Dictionary getDictionary()
    {
        return dictionary;
    }

    public ArchiveManager getArchiveManager()
    {
        return archiveManager;
    }

    /**
     * Return the message network for this node.
     * 
     * @return The message network.
     */
    public Network<UUID> getMessageNetwork()
    {
        return messageNetwork;
    }
    
    /**
     * Return the message storage for this node.
     * 
     * @return The message storage.
     */
    public Storage<Message> getMessageStorage()
    {
        return messageStorage;
    }
    
    public Network<UUID> getProfileNetwork()
    {
        return profileNetwork;
    }
    
    public Storage<Profile> getProfileStorage()
    {
        return profileStorage;
    }

    /**
     * Check that this is a valid command. Either a command that we've issued to
     * execute upon ourselves at a later date, or a command signed by someone
     * who we trust.
     * 
     * @param command
     */
    public Verification verify(Synapse synapse)
    {
        return new Verification(new NodeExecutor(this, synapse), "OK");
    }

    /**
     * Add a synapse listener to the node.
     * 
     * @param listener
     *            A synapse listener.
     */
    public void addListener(SynapseListener listener)
    {
        listOfListeners.add(listener);
    }

    /**
     * Remove a synapse listener from the node. A synapse listener is identified
     * by its object identity.
     * 
     * @param listener
     *            A synapse listener to remove.
     */
    public void removeListener(SynapseListener listener)
    {
        while (listOfListeners.remove(listener))
        {
        }
    }

    /**
     * Return an unmodifiable collection of the listeners listening to events on
     * this node.
     * 
     * @return An unmodifiable collection of listeners.
     */
    public Collection<SynapseListener> listeners()
    {
        return Collections.unmodifiableCollection(listOfListeners);
    }

    public UUID newCallback(Runnable runnable)
    {
        UUID callbackId = UUID.randomUUID();
        callbacks.put(callbackId, runnable);
        return callbackId;
    }

    public void callback(UUID callbackId)
    {
        Runnable runnable = callbacks.remove(callbackId);
        if (runnable != null)
        {
            if (calledback.size() == 256)
            {
                calledback.removeFirst();
            }
            calledback.addLast(callbackId);
            runnable.run();
        }
    }

    public void sendCommand(URL url, Synapse synapse)
    {
        try
        {
            envelopes.put(new EnvelopeSendTask(new Envelope(url, synapse)));
        }
        catch (InterruptedException e)
        {
        }
    }

    public boolean actuallySendCommand()
    {
        try
        {
            Task task = envelopes.take();
            if (task.isTerminal())
            {
                return false;
            }
            task.perform();
        }
        catch (InterruptedException e)
        {
        }
        return true;
    }
}
