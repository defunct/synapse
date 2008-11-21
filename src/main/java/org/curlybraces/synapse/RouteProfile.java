package org.curlybraces.synapse;

import java.util.UUID;

public class RouteProfile extends Command
{
    private UUID profileId;
    
    public RouteProfile()
    {
    }
    
    public RouteProfile(Stamp stamp, UUID profileId)
    {
        super(stamp);
        this.profileId = profileId;
    }
    
    @Override
    public boolean isTerminal()
    {
        return false;
    }
    
    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        Network<UUID> messages = node.getProfileNetwork();
        Router<UUID> router = messages.get(messages.getRootId());
        Route route = router.get(profileId);
        if (!route.isLeaf())
        {
            synapse.shift(new RouteProfile(node.newStamp(), profileId));
        }
        queue.enqueue(route.get(synapse), synapse);
    }
}
