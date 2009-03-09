package org.curlybraces.synapse;

import java.net.URL;

// TODO Document.
public class GoTo
extends Command
{
    // TODO Document.
    private URL url;
    
    // TODO Document.
    public GoTo()
    {
    }
    
    // TODO Document.
    public GoTo(Stamp stamp, URL url)
    {
        super(stamp);
        this.url = url;
    }
    
    // TODO Document.
    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        queue.enqueue(url, synapse);
    }
}
