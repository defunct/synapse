package org.curlybraces.synapse;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Node
{
    public final UUID MIN_UUID = new UUID(Long.MIN_VALUE, Long.MIN_VALUE);
    
    public final UUID MAX_UUID = new UUID(Long.MAX_VALUE, Long.MAX_VALUE);
    
    private final UUID id;
    
    private final Dictionary dictionary;
        
    private final Map<UUID, Archive> mapOfArchives;

    private final List<NodeListener> listOfListeners;

    private final Map<UUID, Runnable> callbacks;

    private final LinkedList<UUID> calledback;

    private final Network<String> tokenNetwork;
    
    /** The message network. */
    private final Network<UUID> messageNetwork;
    
    /** The message storage. */
    private final Storage<Message> messageStorage;
    
    private final Network<UUID> profileNetwork;
    
    private final Storage<Profile> profileStorage;

    private URL url;

    public Node()
    {
        UUID id = UUID.randomUUID();
        
        Storage<Message> messageStorage = new Storage<Message>();
        messageStorage.create(MIN_UUID, MAX_UUID);

        Route route = new Route(id, true);
        Network<String> tokenNetwork = new Network<String>(UUID.randomUUID(), "");
        tokenNetwork.get(tokenNetwork.getRootId()).add("", route);
        
        Dictionary dictionary = new Dictionary();
        dictionary.create(Volume.MIN_WORD, Volume.MAX_WORD);
        
        route = new Route(id, true);
        Network<UUID> messageNetwork = new Network<UUID>(UUID.randomUUID(), MIN_UUID);
        messageNetwork.get(messageNetwork.getRootId()).add(MIN_UUID, route);
        
        Storage<Profile> profileStorage = new Storage<Profile>();
        profileStorage.create(MIN_UUID, MAX_UUID);
        
        route = new Route(id, true);
        Network<UUID> profileNetwork = new Network<UUID>(UUID.randomUUID(), MIN_UUID);
        profileNetwork.get(profileNetwork.getRootId()).add(MIN_UUID, route);

        this.id = id;
        this.mapOfArchives = new HashMap<UUID, Archive>();
        this.listOfListeners = new ArrayList<NodeListener>();
        this.callbacks = new HashMap<UUID, Runnable>();
        this.calledback = new LinkedList<UUID>();

        this.tokenNetwork = tokenNetwork;
        this.dictionary = dictionary;

        this.messageNetwork = messageNetwork;
        this.messageStorage = messageStorage;
        
        this.profileNetwork = profileNetwork;
        this.profileStorage = profileStorage;
    }

    public UUID getId()
    {
        return id;
    }

    public void setURL(URL url)
    {
        this.url = url;
        getTokenNetwork().get(getTokenNetwork().getRootId()).get(Volume.MIN_WORD).add(url);
        getMessageNetwork().get(getMessageNetwork().getRootId()).get(MIN_UUID).add(url);
        getProfileNetwork().get(getProfileNetwork().getRootId()).get(MIN_UUID).add(url);
    }

    public URL getURL()
    {
        return url;
    }
    
    public void execute(Synapse synapse)
    {
        new NodeExecutor(this, synapse).execute();
    }

    public Dictionary getDictionary()
    {
        return dictionary;
    }

    public Archive getArchive(UUID profileId)
    {
        Archive archive = mapOfArchives.get(profileId);
        if (archive == null)
        {
            archive = new Archive(profileId);
            mapOfArchives.put(profileId, archive);
        }
        return archive;
    }
    
    /**
     * Return the token network for this node.
     * 
     * @return The token network.
     */
    public Network<String> getTokenNetwork()
    {
        return tokenNetwork;
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
    public void addListener(NodeListener listener)
    {
        synchronized (listOfListeners)
        {
            listOfListeners.add(listener);
        }
    }

    /**
     * Remove a synapse listener from the node. A synapse listener is identified
     * by its object identity.
     * 
     * @param listener
     *            A synapse listener to remove.
     */
    public void removeListener(NodeListener listener)
    {
        synchronized (listOfListeners)
        {
            while (listOfListeners.remove(listener))
            {
            }
        }
    }

    /**
     * Return an unmodifiable collection of the listeners listening to events on
     * this node.
     * 
     * @return An unmodifiable collection of listeners.
     */
    public Collection<NodeListener> listeners()
    {
        List<NodeListener> copy;
        synchronized (listOfListeners)
        {
            copy = new ArrayList<NodeListener>(listOfListeners);
        }
        return copy;
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
}
