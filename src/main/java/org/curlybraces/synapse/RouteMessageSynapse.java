package org.curlybraces.synapse;

import java.util.UUID;

public class RouteMessageSynapse extends Command
{
    private UUID messageId;
    
    public RouteMessageSynapse()
    {
    }
    
    public RouteMessageSynapse(UUID messageId)
    {
        this.messageId = messageId;
    }
    
    @Override
    public void execute(Node node, Synapse synapse)
    {
        Network<UUID> messages = node.getMessageNetwork();
        Router<UUID> router = messages.get(messages.getRootId());
        Route route = router.get(messageId);
        if (!route.isLeaf())
        {
            synapse.shift(new RouteMessageSynapse(messageId));
        }
        route.sendCommand(synapse);
    }
}
