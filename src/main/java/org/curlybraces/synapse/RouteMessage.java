package org.curlybraces.synapse;

import java.util.UUID;

public class RouteMessage extends Command
{
    private UUID messageId;
    
    public RouteMessage()
    {
    }
    
    public RouteMessage(UUID messageId)
    {
        this.messageId = messageId;
    }
    
    @Override
    public boolean isTerminal()
    {
        return false;
    }

    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        Network<UUID> messages = node.getMessageNetwork();
        Router<UUID> router = messages.get(messages.getRootId());
        Route route = router.get(messageId);
        if (!route.isLeaf())
        {
            synapse.shift(new RouteMessage(messageId));
        }
        queue.enqueue(route.get(synapse), synapse);
    }
}
