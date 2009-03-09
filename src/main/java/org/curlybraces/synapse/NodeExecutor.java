package org.curlybraces.synapse;

import java.util.LinkedList;

// TODO Document.
public class NodeExecutor implements Executor
{
    // TODO Document.
    private final Node node;
    
    // TODO Document.
    private final Synapse synapse;
    
    // TODO Document.
    public NodeExecutor(Node node, Synapse synapse)
    {
        this.node = node;
        this.synapse = synapse;
    }

    // TODO Document.
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
