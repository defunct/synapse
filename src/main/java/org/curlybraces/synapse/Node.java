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

    private final UUID id;

    private final Dictionary dictionary;

    private final ArchiveManager archiveManager;

    private final List<SynapseListener> listOfListeners;

    private final Network<UUID> messageNetwork;

    private final Map<UUID, Runnable> callbacks;

    private final LinkedList<UUID> calledback;

    private final LinkedBlockingQueue<Task> envelopes;
    
    private final Storage<Message> messages;

    private Thread mailman;

    private URL url;

    @Inject
    public Node(Dictionary siloManager, ArchiveManager archiveManager)
    {
        this.id = UUID.randomUUID();
        this.dictionary = siloManager;
        this.archiveManager = archiveManager;
        this.messageNetwork = new Network<UUID>(UUID.randomUUID(), new UUID(0L, 0L));
        this.listOfListeners = new ArrayList<SynapseListener>();
        this.callbacks = new HashMap<UUID, Runnable>();
        this.calledback = new LinkedList<UUID>();
        this.envelopes = new LinkedBlockingQueue<Task>();
        this.messages = new Storage<Message>();
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
        getMessageNetwork().get(getMessageNetwork().getRootId()).get(
                new UUID(0L, 0L)).add(url);
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

    public Network<UUID> getMessageNetwork()
    {
        return messageNetwork;
    }
    
    public Storage<Message> getMessageStorage()
    {
        return messages;
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
