package org.curlybraces.synapse;

public class InjectProfile extends Command
{
    private Profile profile;
    
    public InjectProfile()
    {
    }
    
    public InjectProfile(Stamp stamp, Profile profile)
    {
        super(stamp);
        this.profile = profile;
    }
    
    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        Bin<Profile> bin = node.getProfileStorage().get(profile.getId());
        if (bin == null)
        {
            System.out.println("No bin found.");
        }
        else
        {
            bin.put(profile.getId(), profile);
        }
        queue.enqueue(synapse);
    }
}
