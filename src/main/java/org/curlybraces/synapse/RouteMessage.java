package org.curlybraces.synapse;

import java.util.UUID;

// TODO Document.
public class RouteMessage extends Command
{
    // TODO Document.
    private UUID messageId;
    
    // TODO Document.
    public RouteMessage()
    {
    }
    
    // TODO Document.
    public RouteMessage(Stamp stamp, UUID messageId)
    {
        super(stamp);
        this.messageId = messageId;
    }

    // TODO Document.
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
