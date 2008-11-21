package org.curlybraces.synapse;

import java.util.UUID;

public class ExecuteCallback extends Command
{
    private UUID callbackId;

    public ExecuteCallback()
    {
    }

    public ExecuteCallback(Stamp stamp, UUID callbackId)
    {
        super(stamp);
        this.callbackId = callbackId;
    }

    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        Runnable runnable = node.getVoidCallbacks().callback(callbackId);
        if (runnable != null)
        {
            runnable.run();
        }
    }
}
