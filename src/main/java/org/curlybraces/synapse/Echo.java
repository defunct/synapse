package org.curlybraces.synapse;

// TODO Document.
public class Echo extends Command
{
    // TODO Document.
    private String message;
    
    // TODO Document.
    public Echo()
    {
    }

    // TODO Document.
    public Echo(Stamp stamp, String message)
    {
        super(stamp);
        this.message = message;
    }

    // TODO Document.
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        System.out.println(message);
    }
}
