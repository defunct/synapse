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
        Bin<Message> bin = node.getMessageStorage().get(message.getId());
        if (bin == null)
        {
            System.out.println("No bin found.");
        }
        else
        {
            System.out.println(message.getText());
        }
    }
}
