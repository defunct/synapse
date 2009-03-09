package org.curlybraces.synapse;

import java.net.URL;
import java.util.LinkedList;

// TODO Document.
public class SynapseQueue
{
    // TODO Document.
    private final Node node;

    // TODO Document.
    private final LinkedList<Executor> listOfExecutors;
    
    // TODO Document.
    public SynapseQueue(Node node, LinkedList<Executor> listOfExecutors)
    {
        this.node = node;
        this.listOfExecutors = listOfExecutors;
    }
    
    // TODO Document.
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
    
    // TODO Document.
    public void enqueue(Synapse synapse)
    {
        listOfExecutors.addLast(new SynapseExecutor(node, this, synapse));
    }
}
