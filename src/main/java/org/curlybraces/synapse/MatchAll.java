package org.curlybraces.synapse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// TODO Document.
public class MatchAll
{
    // TODO Document.
    public final List<MatchAny> listOfAny;
    
    // TODO Document.
    public MatchAll()
    {
        this.listOfAny = new ArrayList<MatchAny>();
    }
    
    // TODO Document.
    public void add(MatchAny any)
    {
        listOfAny.add(any);
    }
    
    // TODO Document.
    public Set<Set<Term>> getTerms()
    {
        Set<Set<Term>> setOfSetsOfTerms = new HashSet<Set<Term>>();
        for (MatchAny any : listOfAny)
        {
            setOfSetsOfTerms.add(any.getTerms());
        }
        return setOfSetsOfTerms;
    }
}
