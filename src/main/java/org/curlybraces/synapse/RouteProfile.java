package org.curlybraces.synapse;

import java.util.UUID;

public class RouteProfile extends Command
{
    private UUID profileId;
    
    public RouteProfile()
    {
    }
    
    public RouteProfile(UUID profileId)
    {
        this.profileId = profileId;
    }
    
    @Override
    public boolean isTerminal()
    {
        return false;
    }
    
    @Override
    public void execute(Node node, Synapse synapse)
    {
        Network<UUID> messages = node.getProfileNetwork();
        Router<UUID> router = messages.get(messages.getRootId());
        Route route = router.get(profileId);
        if (!route.isLeaf())
        {
            synapse.shift(new RouteProfile(profileId));
        }
        node.sendCommand(route.get(synapse), synapse);
    }
}
