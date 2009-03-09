package org.curlybraces.synapse;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// TODO Document.
public class Node implements Contributor
{
    // TODO Document.
    public final UUID MIN_UUID = new UUID(Long.MIN_VALUE, Long.MIN_VALUE);
    
    // TODO Document.
    public final UUID MAX_UUID = new UUID(Long.MAX_VALUE, Long.MAX_VALUE);
    
    // TODO Document.
    private final UUID id;
    
    // TODO Document.
    private final Dictionary dictionary;
        
    // TODO Document.
    private final Map<UUID, Archive> mapOfArchives;

    // TODO Document.
    private final List<NodeListener> listOfListeners;

    // TODO Document.
    private final CallbackMap<Runnable> voidCallbacks;
    
    // TODO Document.
    private final CallbackMap<TokenCallback> tokenCallbacks;
    
    // TODO Document.
    private final CallbackMap<MessageCallback> messageCallbacks;
    
    // TODO Document.
    private final CallbackMap<ProfileCallback> profileCallbacks;

    // TODO Document.
    private final Network<Term> tokenNetwork;
    
    /** The message network. */
    private final Network<UUID> messageNetwork;
    
    /** The message storage. */
    private final Storage<Message> messageStorage;
    
    // TODO Document.
    private final Network<UUID> profileNetwork;
    
    // TODO Document.
    private final Storage<Profile> profileStorage;

    // TODO Document.
    private URL url;

    // TODO Document.
    public Node()
    {
        UUID id = UUID.randomUUID();
        
        Storage<Message> messageStorage = new Storage<Message>();
        messageStorage.create(MIN_UUID, MAX_UUID);

        Route route = new Route(id, true);
        Network<Term> tokenNetwork = new Network<Term>(UUID.randomUUID(), Term.MIN_TERM);
        tokenNetwork.get(tokenNetwork.getRootId()).add(Term.MIN_TERM, route);
        
        Dictionary dictionary = new Dictionary();
        dictionary.create(Term.MIN_TERM, Term.MAX_TERM);
        
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

        this.voidCallbacks = new CallbackMap<Runnable>();
        this.tokenCallbacks = new CallbackMap<TokenCallback>();
        this.messageCallbacks = new CallbackMap<MessageCallback>();
        this.profileCallbacks = new CallbackMap<ProfileCallback>();

        this.tokenNetwork = tokenNetwork;
        this.dictionary = dictionary;

        this.messageNetwork = messageNetwork;
        this.messageStorage = messageStorage;
        
        this.profileNetwork = profileNetwork;
        this.profileStorage = profileStorage;
    }

    // TODO Document.
    public UUID getId()
    {
        return id;
    }

    // TODO Document.
    public void setURL(URL url)
    {
        this.url = url;
        getTokenNetwork().get(getTokenNetwork().getRootId()).get(Term.MIN_TERM).add(url);
        getMessageNetwork().get(getMessageNetwork().getRootId()).get(MIN_UUID).add(url);
        getProfileNetwork().get(getProfileNetwork().getRootId()).get(MIN_UUID).add(url);
    }
    
    // TODO Document.
    public UUID newUUID()
    {
        return UUID.randomUUID();
    }
    
    // TODO Document.
    public Stamp newStamp()
    {
        return new Stamp(UUID.randomUUID(), new Date());
    }

    // TODO Document.
    public URL getURL()
    {
        return url;
    }
    
    // TODO Document.
    public void setProfile(Profile profile)
    {
        new NodeExecutor(this, new Synapse(new SetProfile(newStamp(), profile))).execute();
    }
    
    // TODO Document.
    public void update(Message message)
    {
        new NodeExecutor(this, new Synapse(new Update(newStamp(), message))).execute();
    }
    
    // TODO Document.
    public void search(MatchAll search, NodeListener listener)
    {
        new MergeAll(this, search, 3, listener).next();
    }

    // TODO Document.
    public Dictionary getDictionary()
    {
        return dictionary;
    }

    // TODO Document.
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
    public Network<Term> getTokenNetwork()
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
    
    // TODO Document.
    public Network<UUID> getProfileNetwork()
    {
        return profileNetwork;
    }
    
    // TODO Document.
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
    
    // TODO Document.
    public CallbackMap<Runnable> getVoidCallbacks()
    {
        return voidCallbacks;
    }
    
    // TODO Document.
    public CallbackMap<TokenCallback> getTokenCallbacks()
    {
        return tokenCallbacks;
    }
    
    // TODO Document.
    public CallbackMap<MessageCallback> getMessageCallbacks()
    {
        return messageCallbacks;
    }
    
    // TODO Document.
    public CallbackMap<ProfileCallback> getProfileCallbacks()
    {
        return profileCallbacks;
    }
}
