package org.curlybraces.synapse;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

// TODO Document.
public class Storage<T>
{
    // TODO Document.
    private final SortedMap<UUID, Bin<T>> mapOfBins;
    
    // TODO Document.
    public Storage()
    {
        this.mapOfBins = new TreeMap<UUID, Bin<T>>(Collections.reverseOrder());
    }
    
    // TODO Document.
    public void create(UUID min, UUID max)
    {
        Bin<T> bin = new Bin<T>(max);
        mapOfBins.put(min, bin);
    }
    
    // TODO Document.
    public Bin<T> get(UUID id)
    {
        SortedMap<UUID, Bin<T>> tail = mapOfBins.tailMap(id);
        if (tail.size() != 0)
        {
            Bin<T> bin = tail.get(tail.firstKey());
            if (id.compareTo(bin.getEnd()) < 0)
            {
                return bin;
            }
        }
        return null;
    }
}
