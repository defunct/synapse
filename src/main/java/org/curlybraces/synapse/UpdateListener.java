package org.curlybraces.synapse;


public class UpdateListener
{
    private final Node node;
    
    private final Message message;

    private int count;
    
    public UpdateListener(Node node, Message message, int count)
    {
        this.node = node;
        this.message = message;
        this.count = count;
    }
    
    public void updated()
    {
        if (--count == 0)
        {
            for (NodeListener listener : node.listeners())
            {
                listener.update(message);
            }
        }
    }
}