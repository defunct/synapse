package org.curlybraces.synapse;

import java.util.UUID;

public class RouteMessage extends Command
{
    private UUID messageId;
    
    public RouteMessage()
    {
    }
    
    public RouteMessage(Stamp stamp, UUID messageId)
    {
        super(stamp);
        this.messageId = messageId;
    }

    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        Network<UUID> messages = node.getMessageNetwork();
        Router<UUID> router = messages.get(messages.getRootId());
        Route route = router.get(messageId);
        if (!route.isLeaf())
        {
            synapse.shift(new RouteMessage(node.newStamp(), messageId));
        }
        queue.enqueue(route.get(synapse), synapse);
    }
}
