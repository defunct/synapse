package org.curlybraces.synapse;

import java.util.UUID;

public class SetProfile extends Command
{
    private Profile profile;

    public SetProfile()
    {
    }
    
    public SetProfile(Profile profile)
    {
        this.profile = profile;
    }
    
    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        UUID callbackId = node.newCallback(new SetProfileCallback(node, profile));
        Synapse inject = new Synapse(new RouteProfile(profile.getId()),
                                     new InjectProfile(profile),
                                     new Callback(node.getURL()),
                                     new ExecuteCallback(callbackId));
        queue.enqueue(inject);
    }
}
