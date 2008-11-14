package org.curlybraces.synapse;

public class NodeExecutor implements Executor
{
    private final Node node;
    
    private final Synapse synapse;
    
    public NodeExecutor(Node node, Synapse synapse)
    {
        this.node = node;
        this.synapse = synapse;
    }

    public void execute()
    {
        synapse.execute(node);
    }
}
