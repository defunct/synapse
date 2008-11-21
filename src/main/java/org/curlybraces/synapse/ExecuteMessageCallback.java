package org.curlybraces.synapse;

import java.util.UUID;

public class ExecuteMessageCallback extends Command
{
    private UUID callbackId;
    
    private Message message;
    
    public ExecuteMessageCallback()
    {
    }
    
    public ExecuteMessageCallback(Stamp stamp, UUID callbackId, Message message)
    {
        super(stamp);
        this.callbackId = callbackId;
        this.message = message;
    }
    
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
