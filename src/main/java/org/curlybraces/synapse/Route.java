package org.curlybraces.synapse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Route
{
    private final UUID id;
    
    private final boolean leaf;

    private final List<Locator> listOfLocators;
    
    public Route(UUID id, boolean leaf)
    {
        this.id = id;
        this.leaf = leaf;
        this.listOfLocators = new ArrayList<Locator>();
    }
    
    public UUID getId()
    {
        return id;
    }
    
    public boolean isLeaf()
    {
        return leaf;
    }
    
    public void add(Locator locator)
    {
        if (!listOfLocators.contains(locator))
        {
            listOfLocators.add(locator);
        }
    }
    
    public Result sendCommand(Synapse synapse)
    {
        int size = listOfLocators.size();
        switch (size)
        {
        case 0:
            // Route the 
            throw new UnsupportedOperationException("Not implemeneted.");
        case 1:
            return listOfLocators.get(0).sendCommand(synapse);
        default:
            int index = (synapse.getId().hashCode() % size);
            return listOfLocators.get(index).sendCommand(synapse);
        }
    }
}
