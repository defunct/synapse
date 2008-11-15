package org.curlybraces.synapse;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Route
{
    private final UUID id;
    
    private final boolean leaf;

    private final List<URL> listOfURLs;
    
    public Route(UUID id, boolean leaf)
    {
        this.id = id;
        this.leaf = leaf;
        this.listOfURLs = new ArrayList<URL>();
    }
    
    public UUID getId()
    {
        return id;
    }
    
    public boolean isLeaf()
    {
        return leaf;
    }
    
    public void add(URL url)
    {
        if (!listOfURLs.contains(url))
        {
            listOfURLs.add(url);
        }
    }
    
    public URL get(Synapse synapse)
    {
        int size = listOfURLs.size();
        switch (size)
        {
        case 0:
            // Route the 
            throw new UnsupportedOperationException("Not implemeneted.");
        case 1:
            return listOfURLs.get(0);
        default:
            int index = (synapse.getId().hashCode() % size);
            return listOfURLs.get(index);
        }
    }
}
