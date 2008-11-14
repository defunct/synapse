package org.curlybraces.synapse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jibx.runtime.JiBXException;

public class Route
{
    private final UUID id;

    private final List<Locator> listOfLocators;
    
    public Route(UUID id)
    {
        this.id = id;
        this.listOfLocators = new ArrayList<Locator>();
    }
    
    public UUID getId()
    {
        return id;
    }
    
    public void add(Locator locator)
    {
        if (!listOfLocators.contains(locator))
        {
            listOfLocators.add(locator);
        }
    }
    
    public Result sendCommand(Synapse synapse) throws IOException, JiBXException
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
