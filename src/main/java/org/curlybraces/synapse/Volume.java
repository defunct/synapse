package org.curlybraces.synapse;

import java.util.Map;
import java.util.TreeMap;

public class Volume
{
    /** The minimum value for a word. */
    public final static String MIN_WORD = "";
    
    public final static String MAX_WORD = "\uffff\0";
    
    private final Map<String, Entry> mapOfEntries;

    private final String end;

    public Volume(String end)
    {
        this.end = end;
        this.mapOfEntries = new TreeMap<String, Entry>();
    }

    /**
     * Return the end value, the successor value to the maximum key value that
     * can be stored in this bin.
     * 
     * @return The end value.
     */
    public String getEnd()
    {
        return end;
    }

    /**
     * Map the term to the specified entry.
     * 
     * @param term
     *            The term.
     * @param entry
     *            The entry.
     */
    public void put(String term, Entry entry)
    {
        mapOfEntries.put(term, entry);
    }

    /**
     * Return the entry for the specified term.
     * 
     * @param term
     *            The term.
     * @return The entry for the term, or <code>null</code> if the term is not
     *         present in this volume.
     */
    public Entry get(String term)
    {
        return mapOfEntries.get(term);
    }
}
