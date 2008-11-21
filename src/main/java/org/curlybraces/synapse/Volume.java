package org.curlybraces.synapse;

import java.util.Map;
import java.util.TreeMap;

public class Volume
{
    private final Map<Term, Entry> mapOfEntries;

    private final Term end;

    public Volume(Term end)
    {
        this.end = end;
        this.mapOfEntries = new TreeMap<Term, Entry>();
    }

    /**
     * Return the end value, the successor value to the maximum key value that
     * can be stored in this bin.
     * 
     * @return The end value.
     */
    public Term getEnd()
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
    public void put(Term term, Entry entry)
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
    public Entry get(Term term)
    {
        return mapOfEntries.get(term);
    }
}
