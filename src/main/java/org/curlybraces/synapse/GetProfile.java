package org.curlybraces.synapse;

import java.net.URL;
import java.util.UUID;

// TODO Document.
public class GetProfile extends Command
{
    // TODO Document.
    private UUID profileId;
    
    // TODO Document.
    private URL from;
    
    // TODO Document.
    private UUID callbackId;
    
    // TODO Document.
    public GetProfile()
    {
    }

    // TODO Document.
    public GetProfile(Stamp stamp, UUID profileId, URL from, UUID callbackId)
    {
        super(stamp);
        this.profileId = profileId;
        this.from = from;
        this.callbackId = callbackId;
    }
    
    // TODO Document.
    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        Bin<Profile> bin = node.getProfileStorage().get(profileId);
        Profile profile = null;
        if (bin != null)
        {
            profile = bin.get(profileId);
        }
        Synapse callback = new Synapse(
                new GoTo(node.newStamp(), from),
                new ExecuteProfileCallback(node.newStamp(), callbackId, profile)
        );
        queue.enqueue(callback);
    }
}
