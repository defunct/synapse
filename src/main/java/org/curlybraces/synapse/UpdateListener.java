package org.curlybraces.synapse;

// TODO Document.
public class UpdateListener
{
    // TODO Document.
    private final Node node;
    
    // TODO Document.
    private final Message message;

    // TODO Document.
    private int count;
    
    // TODO Document.
    public UpdateListener(Node node, Message message, int count)
    {
        this.node = node;
        this.message = message;
        this.count = count;
    }
    
    // TODO Document.
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