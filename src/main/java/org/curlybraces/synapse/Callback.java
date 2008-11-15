package org.curlybraces.synapse;

import java.net.URL;


public class Callback
extends Command
{
    private URL url;
    
    public Callback()
    {
    }
    
    public Callback(URL url)
    {
        this.url = url;
    }
    
    @Override
    public boolean isTerminal()
    {
        return false;
    }
    
    @Override
    public void execute(Node node, Synapse synapse)
    {
        node.sendCommand(url, synapse);
    }
}
