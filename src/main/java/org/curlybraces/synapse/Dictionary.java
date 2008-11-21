package org.curlybraces.synapse;

import java.util.SortedMap;
import java.util.TreeMap;

public class Dictionary
{
    private final SortedMap<Term, Volume> mapOfVolumes;
    
    public Dictionary()
    {
        this.mapOfVolumes = new TreeMap<Term, Volume>(new Reverse<Term>());
    }
    
    public void create(Term min, Term max)
    {
        Volume volume = new Volume(max);
        mapOfVolumes.put(min, volume);
    }

    public Volume get(Term term)
    {
        SortedMap<Term, Volume> tail = mapOfVolumes.tailMap(term);
        
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
