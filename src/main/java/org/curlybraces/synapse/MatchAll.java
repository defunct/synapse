package org.curlybraces.synapse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MatchAll
{
    public final List<MatchAny> listOfAny;
    
    public MatchAll()
    {
        this.listOfAny = new ArrayList<MatchAny>();
    }
    
    public void add(MatchAny any)
    {
        listOfAny.add(any);
    }
    
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
