package org.curlybraces.synapse;

// TODO Document.
public class SynapseExecutor implements Executor
{
    // TODO Document.
    private final Node node;
    
    // TODO Document.
    private final SynapseQueue queue;
    
    // TODO Document.
    private final Synapse synapse;
    
    // TODO Document.
    public SynapseExecutor(Node node, SynapseQueue queue, Synapse synapse)
    {
        this.node = node;
        this.queue = queue;
        this.synapse = synapse;
    }
    
    // TODO Document.
    public void execute()
    {
        synapse.execute(node, queue);
    }
}
