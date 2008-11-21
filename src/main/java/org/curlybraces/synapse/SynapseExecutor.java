package org.curlybraces.synapse;

public class SynapseExecutor implements Executor
{
    private final Node node;
    
    private final SynapseQueue queue;
    
    private final Synapse synapse;
    
    public SynapseExecutor(Node node, SynapseQueue queue, Synapse synapse)
    {
        this.node = node;
        this.queue = queue;
        this.synapse = synapse;
    }
    
    public void execute()
    {
        synapse.execute(node, queue);
    }
}
