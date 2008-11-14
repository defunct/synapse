package org.curlybraces.synapse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.inject.Inject;

public class Node
{
    private final UUID id;

    private final SiloManager siloManager;

    private final ArchiveManager archiveManager;

    private final Map<UUID, Router<Term>> mapOfSearchRouters;

    private final UUID rootSearchRouterId;

    private UpdateListener updateListener;

    @Inject
    public Node(SiloManager siloManager, ArchiveManager archiveManager)
    {
        Route route = new Route(UUID.randomUUID());
        Router<String> rootSearchRouter = new Router<String>(UUID.randomUUID(),
                "", route);
        Map<UUID, Router<Term>> mapOfSearchRouters = new HashMap<UUID, Router<Term>>();

        this.id = UUID.randomUUID();
        this.siloManager = siloManager;
        this.archiveManager = archiveManager;
        this.updateListener = new UpdateListener();
        this.rootSearchRouterId = rootSearchRouter.getId();
        this.mapOfSearchRouters = mapOfSearchRouters;
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

    public Router<Term> getSearchRouter(UUID searchRouterId)
    {
        return mapOfSearchRouters.get(searchRouterId);
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

    public UUID getRootSearchRouterId()
    {
        return rootSearchRouterId;
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
