package org.curlybraces.synapse;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Manages a collection of volumes within a node.
 * 
 * @author Alan Gutierrez
 */
public class Dictionary
{
    // TODO Document.
    private final SortedMap<Term, Volume> mapOfVolumes;
    
    // TODO Document.
    public Dictionary()
    {
        this.mapOfVolumes = new TreeMap<Term, Volume>(Collections.reverseOrder());
    }
    
    // TODO Document.
    public void create(Term min, Term max)
    {
        Volume volume = new Volume(max);
        mapOfVolumes.put(min, volume);
    }

    // TODO Document.
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
