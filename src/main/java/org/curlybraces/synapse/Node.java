package org.curlybraces.synapse;

import java.util.UUID;

import com.google.inject.Inject;

public class Node
{
    private final UUID id;
    
    private final SiloManager siloManager;
    
    private final ArchiveManager archiveManager;
    
    private UpdateListener updateListener;
    
    @Inject
    public Node(SiloManager siloManager, ArchiveManager archiveManager)
    {
        this.id = UUID.randomUUID();
        this.siloManager = siloManager;
        this.archiveManager = archiveManager;
        this.updateListener = new UpdateListener();
    }
    
    public UUID getId()
    {
        return id;
    }
    
    public SiloManager getSiloManager()
    {
        return siloManager;
    }
    
    public ArchiveManager getArchiveManager()
    {
        return archiveManager;
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
    
    public void setUpdateListener(UpdateListener updateListener)
    {
        this.updateListener = updateListener;
    }

    public UpdateListener getUpdateListener()
    {
        return updateListener;
    }
}
