package org.curlybraces.synapse;

import java.util.UUID;

public class ExecuteCallback
extends Command
{
    private UUID callbackId;
    
    public ExecuteCallback()
    {
    }
    
    public ExecuteCallback(UUID callbackId)
    {
        this.callbackId = callbackId;
    }
    
    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        node.callback(callbackId);
    }
}
