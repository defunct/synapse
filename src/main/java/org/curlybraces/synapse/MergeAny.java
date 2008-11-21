package org.curlybraces.synapse;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MergeAny implements TokenCallback
{
    private final Set<Term> setOfTerms;
    
    private final List<Token> listOfTokens;

    private final MergeAll all;

    private int count;

    public MergeAny(MergeAll all, Set<Term> setOfTerms)
    {
        this.setOfTerms = setOfTerms;
        this.all = all;
        this.count = setOfTerms.size();
        this.listOfTokens = new ArrayList<Token>();
    }

    public void run(Token[] tokens)
    {
        if (count == 0)
        {
            throw new IllegalStateException();
        }
        for (int i = 0; i < tokens.length; i++)
        {
            listOfTokens.add(tokens[i]);
        }
        if (--count == 0)
        {
            all.merge(setOfTerms, listOfTokens);
        }
    }
}
