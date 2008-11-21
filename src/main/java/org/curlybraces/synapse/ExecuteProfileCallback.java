package org.curlybraces.synapse;

import java.util.UUID;

public class ExecuteProfileCallback extends Command
{
    private UUID callbackId;
    
    private Profile profile;
    
    public ExecuteProfileCallback()
    {
    }
    
    public ExecuteProfileCallback(Stamp stamp, UUID callbackId, Profile profile)
    {
        super(stamp);
        this.callbackId = callbackId;
        this.profile = profile;
    }
    
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