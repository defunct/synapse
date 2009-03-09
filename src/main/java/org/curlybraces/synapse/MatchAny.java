package org.curlybraces.synapse;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// TODO Document.
public class MatchAny
{
    // TODO Document.
    private final Set<Term> setOfTerms;
    
    // TODO Document.
    public MatchAny()
    {
        this.setOfTerms = new HashSet<Term>();
    }
    
    // TODO Document.
    public void add(Term term)
    {
        setOfTerms.add(term);
    }
    
    // TODO Document.
    public Set<Term> getTerms()
    {
        return Collections.unmodifiableSet(setOfTerms);
    }
}
