package org.curlybraces.synapse;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

public class Storage<T>
{
    private final SortedMap<UUID, Bin<T>> mapOfBins;
    
    public Storage()
    {
        this.mapOfBins = new TreeMap<UUID, Bin<T>>(new Reverse<UUID>());
    }
    
    public T get(UUID id)
    {
        SortedMap<UUID, Bin<T>> tail = mapOfBins.tailMap(id);
        if (tail.size() != 0)
        {
            Bin<T> bin = tail.get(tail.firstKey());
            if (id.compareTo(bin.getEnd()) < 0)
            {
                return bin.get(id);
            }
        }
        return null;
    }
}