package org.curlybraces.synapse;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// TODO Document.
public class MergeAny implements TokenCallback
{
    // TODO Document.
    private final Set<Term> setOfTerms;
    
    // TODO Document.
    private final List<Token> listOfTokens;

    // TODO Document.
    private final MergeAll all;

    // TODO Document.
    private int count;

    // TODO Document.
    public MergeAny(MergeAll all, Set<Term> setOfTerms)
    {
        this.setOfTerms = setOfTerms;
        this.all = all;
        this.count = setOfTerms.size();
        this.listOfTokens = new ArrayList<Token>();
    }

    // TODO Document.
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
