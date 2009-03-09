package org.curlybraces.synapse;

// TODO Document.
public class InjectProfile extends Command
{
    // TODO Document.
    private Profile profile;
    
    // TODO Document.
    public InjectProfile()
    {
    }
    
    // TODO Document.
    public InjectProfile(Stamp stamp, Profile profile)
    {
        super(stamp);
        this.profile = profile;
    }
    
    // TODO Document.
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
