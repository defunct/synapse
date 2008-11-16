package org.curlybraces.synapse;

public class InjectMessage
extends Command
{
    private Message message;
    
    public InjectMessage()
    {
    }
    
    public InjectMessage(Message missive)
    {
        this.message = missive;
    }
    
    @Override
    public void execute(Node node, Synapse synapse)
    {
        System.out.println(message.getText());
    }
}
