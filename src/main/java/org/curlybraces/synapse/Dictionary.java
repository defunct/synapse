package org.curlybraces.synapse;

import java.util.SortedMap;
import java.util.TreeMap;

public class Dictionary
{
    private final SortedMap<String, Volume> mapOfVolumes;
    
    public Dictionary()
    {
        this.mapOfVolumes = new TreeMap<String, Volume>(new Reverse<String>());
    }
    
    public void create(String min, String max)
    {
        Volume volume = new Volume(max);
        mapOfVolumes.put(min, volume);
    }

    public Volume get(String term)
    {
        SortedMap<String, Volume> tail = mapOfVolumes.tailMap(term);
        
        if (tail.size() != 0)
        {
            Volume volume = tail.get(tail.firstKey());
            if (term.compareTo(volume.getEnd()) < 0)
            {
                return volume;
            }
        }
        
        return null;
    }
}
