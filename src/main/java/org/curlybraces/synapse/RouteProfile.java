package org.curlybraces.synapse;

import java.util.UUID;

// TODO Document.
public class RouteProfile extends Command
{
    // TODO Document.
    private UUID profileId;
    
    // TODO Document.
    public RouteProfile()
    {
    }
    
    // TODO Document.
    public RouteProfile(Stamp stamp, UUID profileId)
    {
        super(stamp);
        this.profileId = profileId;
    }
    
    // TODO Document.
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
