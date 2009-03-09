package org.curlybraces.synapse;

import java.net.URL;
import java.util.UUID;

// TODO Document.
public class GetMessage extends Command
{
    // TODO Document.
    private UUID messageId;
    
    // TODO Document.
    private URL from;
    
    // TODO Document.
    private UUID callbackId;
    
    // TODO Document.
    public GetMessage()
    {
    }

    // TODO Document.
    public GetMessage(Stamp stamp, UUID messageId, URL from, UUID callbackId)
    {
        super(stamp);
        this.messageId = messageId;
        this.from = from;
        this.callbackId = callbackId;
    }
    
    // TODO Document.
    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        Bin<Message> bin = node.getMessageStorage().get(messageId);
        Message message = null;
        if (bin != null)
        {
            message = bin.get(messageId);
        }
        Synapse callback = new Synapse(
                new GoTo(node.newStamp(), from),
                new ExecuteMessageCallback(node.newStamp(), callbackId, message)
        );
        queue.enqueue(callback);
    }
}
