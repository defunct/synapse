package org.curlybraces.synapse;

public class InjectMessage
extends Command
{
    private Missive message;
    
    public InjectMessage()
    {
    }
    
    public InjectMessage(Missive missive)
    {
        this.message = missive;
    }
    
    @Override
    public void execute(Node node, Synapse synapse)
    {
        System.out.println(message.getMessage());
    }
}
