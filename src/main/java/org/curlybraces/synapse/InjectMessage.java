package org.curlybraces.synapse;

// TODO Document.
public class InjectMessage
extends Command
{
    // TODO Document.
    private Message message;
    
    // TODO Document.
    public InjectMessage()
    {
    }
    
    // TODO Document.
    public InjectMessage(Stamp stamp, Message missive)
    {
        super(stamp);
        this.message = missive;
    }
    
    // TODO Document.
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
