package org.curlybraces.synapse;

import java.util.UUID;

// TODO Document.
public class ExecuteCallback extends Command
{
    // TODO Document.
    private UUID callbackId;

    // TODO Document.
    public ExecuteCallback()
    {
    }

    // TODO Document.
    public ExecuteCallback(Stamp stamp, UUID callbackId)
    {
        super(stamp);
        this.callbackId = callbackId;
    }

    // TODO Document.
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
