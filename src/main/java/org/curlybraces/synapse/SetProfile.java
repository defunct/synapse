package org.curlybraces.synapse;

import java.util.UUID;

public class SetProfile extends Command
{
    private Profile profile;

    public SetProfile()
    {
    }
    
    public SetProfile(Stamp stamp, Profile profile)
    {
        super(stamp);
        this.profile = profile;
    }
    
    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        UUID callbackId = node.newUUID();
        node.getVoidCallbacks().put(callbackId, new SetProfileCallback(node, profile));
        Synapse inject = new Synapse(new RouteProfile(node.newStamp(), profile.getId()),
                                     new InjectProfile(node.newStamp(), profile),
                                     new GoTo(node.newStamp(), node.getURL()),
                                     new ExecuteCallback(node.newStamp(), callbackId));
        queue.enqueue(inject);
    }
}
