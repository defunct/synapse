package org.curlybraces.synapse;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// TODO Document.
public class Route
{
    // TODO Document.
    private final UUID id;
    
    // TODO Document.
    private final boolean leaf;

    // TODO Document.
    private final List<URL> listOfURLs;
    
    // TODO Document.
    public Route(UUID id, boolean leaf)
    {
        this.id = id;
        this.leaf = leaf;
        this.listOfURLs = new ArrayList<URL>();
    }
    
    // TODO Document.
    public UUID getId()
    {
        return id;
    }
    
    // TODO Document.
    public boolean isLeaf()
    {
        return leaf;
    }
    
    // TODO Document.
    public void add(URL url)
    {
        if (!listOfURLs.contains(url))
        {
            listOfURLs.add(url);
        }
    }
    
    // TODO Document.
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
