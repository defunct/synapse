package org.curlybraces.synapse;

import java.util.UUID;

// TODO Document.
public class ExecuteProfileCallback extends Command
{
    // TODO Document.
    private UUID callbackId;
    
    // TODO Document.
    private Profile profile;
    
    // TODO Document.
    public ExecuteProfileCallback()
    {
    }
    
    // TODO Document.
    public ExecuteProfileCallback(Stamp stamp, UUID callbackId, Profile profile)
    {
        super(stamp);
        this.callbackId = callbackId;
        this.profile = profile;
    }
    
    // TODO Document.
    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        ProfileCallback callback = node.getProfileCallbacks().callback(callbackId);
        if (callback != null)
        {
            callback.run(profile);
        }
    }
}