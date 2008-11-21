package org.curlybraces.synapse;

import java.util.LinkedList;

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
        LinkedList<Executor> listOfExecutors = new LinkedList<Executor>();
        SynapseQueue queue = new SynapseQueue(node, listOfExecutors);
        queue.enqueue(synapse);
        while (listOfExecutors.size() != 0)
        {
            listOfExecutors.removeFirst().execute();
        }
    }
}
