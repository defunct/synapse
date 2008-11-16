package org.curlybraces.synapse;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class Bin<T>
{
    private final Map<UUID, T> mapOfObjects;
    
    private UUID end;
    
    public Bin(UUID end)
    {
        this.mapOfObjects = new TreeMap<UUID, T>();
        this.end = end;
    }
    
    public void setEnd(UUID next)
    {
        this.end = next;
    }
    
    public UUID getEnd()
    {
        return end;
    }
    
    public void put(UUID id, T object)
    {
        mapOfObjects.put(id, object);
    }
    
    public T get(UUID id)
    {
        return mapOfObjects.get(id);
    }
}
