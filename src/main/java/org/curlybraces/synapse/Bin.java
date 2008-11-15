package org.curlybraces.synapse;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class Bin<T>
{
    private final Map<UUID, T> mapOfObjects;
    
    private T next;
    
    public Bin()
    {
        this.mapOfObjects = new TreeMap<UUID, T>();
    }
    
    public void setNext(T next)
    {
        this.next = next;
    }
    
    public T getNext()
    {
        return next;
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
