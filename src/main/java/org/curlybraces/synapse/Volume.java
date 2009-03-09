package org.curlybraces.synapse;

import java.util.Map;
import java.util.TreeMap;

/**
 * A replicable collection of terms.
 * <p>
 * A volume can mirror itself. The mirror it tighly knit in that all of the
 * mirrors are aware of each other's existence. If there is a split in the
 * volume, all the mirrors are notified of the split.
 * <p>
 * A volume is the end point for an entry. Entries are gathered, mirrors are not
 * notified for each injected term. Instead, after a period of time has passed,
 * the terms are put into historical entries, shuffled back into the entry skip
 * list. Parent is notified of alternates after split like a router.
 * <p>
 * In a search, if a node is receiving an entry once a second, after twenty
 * seconds, it will tell twenty seconds ago in the skip list about these. Twenty
 * seconds ago in the skip list, will tell three minutes ago. Provided no one is
 * injecting a lot of stuff into a year ago, the skip list is going to build new
 * and go old, not adjust much.
 * <p>
 * What if someone is pouring terms into a year ago? Maybe that is not allowed.
 * Maybe it needs to be authorized. How do skip lists grow? 
 * 
 * @author Alan Gutierrez
 */
public class Volume
{
    // TODO Document.
    private final Map<Term, Entry> mapOfEntries;

    // TODO Document.
    private final Term end;

    // TODO Document.
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
