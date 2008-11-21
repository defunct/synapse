package org.curlybraces.synapse;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MatchAny
{
    private final Set<Term> setOfTerms;
    
    public MatchAny()
    {
        this.setOfTerms = new HashSet<Term>();
    }
    
    public void add(Term term)
    {
        setOfTerms.add(term);
    }
    
    public Set<Term> getTerms()
    {
        return Collections.unmodifiableSet(setOfTerms);
    }
}
