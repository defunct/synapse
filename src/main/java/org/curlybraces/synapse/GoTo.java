package org.curlybraces.synapse;

import java.net.URL;


public class GoTo
extends Command
{
    private URL url;
    
    public GoTo()
    {
    }
    
    public GoTo(Stamp stamp, URL url)
    {
        super(stamp);
        this.url = url;
    }
    
    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        queue.enqueue(url, synapse);
    }
}
