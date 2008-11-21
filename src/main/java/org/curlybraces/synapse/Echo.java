package org.curlybraces.synapse;

public class Echo extends Command
{
    private String message;
    
    public Echo()
    {
    }

    public Echo(Stamp stamp, String message)
    {
        super(stamp);
        this.message = message;
    }

    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        System.out.println(message);
    }
}
