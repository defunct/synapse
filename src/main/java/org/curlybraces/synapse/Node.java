package org.curlybraces.synapse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.google.inject.Inject;

public class Node
{
    private final UUID id;

    private final SiloManager siloManager;

    private final ArchiveManager archiveManager;

    private final List<SynapseListener> listOfListeners;
    
    private final Network<UUID> messageNetwork;
    
    private Locator locator;

    @Inject
    public Node(SiloManager siloManager, ArchiveManager archiveManager)
    {
        this.id = UUID.randomUUID();
        this.siloManager = siloManager;
        this.archiveManager = archiveManager;
        this.messageNetwork = new Network<UUID>(UUID.randomUUID(), new UUID(0L, 0L)); 
        this.listOfListeners = new ArrayList<SynapseListener>();
    }

    public UUID getId()
    {
        return id;
    }
    
    public void setLocator(Locator locator)
    {
        this.locator = locator;
        getMessageNetwork().get(getMessageNetwork().getRootId()).get(new UUID(0L, 0L)).add(locator);
    }
    
    public Locator getLocator()
    {
        return locator;
    }

    public SiloManager getSiloManager()
    {
        return siloManager;
    }

    public ArchiveManager getArchiveManager()
    {
        return archiveManager;
    }
    
    public Network<UUID> getMessageNetwork()
    {
        return messageNetwork;
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
}
