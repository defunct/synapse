package org.curlybraces.synapse;

public class InjectMessage
extends Command
{
    private Message message;
    
    public InjectMessage()
    {
    }
    
    public InjectMessage(Stamp stamp, Message missive)
    {
        super(stamp);
        this.message = missive;
    }
    
    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        Bin<Message> bin = node.getMessageStorage().get(message.getId());
        if (bin == null)
        {
            System.out.println("No bin found.");
        }
        else
        {
            bin.put(message.getId(), message);
        }
        queue.enqueue(synapse);
    }
}
