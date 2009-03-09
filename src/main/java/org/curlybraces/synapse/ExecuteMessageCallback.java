package org.curlybraces.synapse;

import java.util.UUID;

// TODO Document.
public class ExecuteMessageCallback extends Command
{
    // TODO Document.
    private UUID callbackId;
    
    // TODO Document.
    private Message message;
    
    // TODO Document.
    public ExecuteMessageCallback()
    {
    }
    
    // TODO Document.
    public ExecuteMessageCallback(Stamp stamp, UUID callbackId, Message message)
    {
        super(stamp);
        this.callbackId = callbackId;
        this.message = message;
    }
    
    // TODO Document.
    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        MessageCallback callback = node.getMessageCallbacks().callback(callbackId);
        if (callback != null)
        {
            callback.run(message);
        }
    }
}
