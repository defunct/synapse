package org.curlybraces.synapse;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer
{
    public List<Term> tokenize(Missive missive)
    {
        List<Term> terms = new ArrayList<Term>();

        Term user = new Term();
        user.setId(missive.getId());
        user.setDate(missive.getDate());
        user.setFlag(Term.USER);
        user.setWord(missive.getPersonId().toString());
        
        terms.add(user);
        
        String[] words = missive.getText().split("\\s+", 0);
        for (String word : words)
        {
            word = word.replaceFirst("^[^\\w\\d]+", "")
                       .replaceFirst("[^\\w\\d]+$", "")
                       .replaceAll("'+", "'")
                       .toLowerCase();
            Term term = new Term();

            term.setId(missive.getId());
            term.setDate(missive.getDate());
            term.setFlag(Term.KEYWORD);
            term.setWord(word);
            
            terms.add(term);
        }
        return terms;
    }
}
