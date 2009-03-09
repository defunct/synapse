package org.curlybraces.synapse;

import java.util.UUID;

// TODO Document.
public class SetProfile extends Command
{
    // TODO Document.
    private Profile profile;

    // TODO Document.
    public SetProfile()
    {
    }
    
    // TODO Document.
    public SetProfile(Stamp stamp, Profile profile)
    {
        super(stamp);
        this.profile = profile;
    }
    
    // TODO Document.
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
