package org.curlybraces.synapse;

import java.net.URL;
import java.util.LinkedList;

public class SynapseQueue
{
    private final Node node;

    private final LinkedList<Executor> listOfExecutors;
    
    public SynapseQueue(Node node, LinkedList<Executor> listOfExecutors)
    {
        this.node = node;
        this.listOfExecutors = listOfExecutors;
    }
    
    public void enqueue(URL url, Synapse synapse)
    {
        if (url.equals(node.getURL()))
        {
            enqueue(synapse);
        }
        else
        {
            listOfExecutors.addLast(new EnvelopeExecutor(new Envelope(url, synapse)));
        }
    }
    
    public void enqueue(Synapse synapse)
    {
        listOfExecutors.addLast(new SynapseExecutor(node, this, synapse));
    }
}
