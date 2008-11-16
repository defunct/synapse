package org.curlybraces.synapse;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer
{
    public List<Term> tokenize(Message message)
    {
        List<Term> terms = new ArrayList<Term>();

        Term user = new Term();
        user.setId(message.getId());
        user.setDate(message.getDate());
        user.setFlag(Term.USER);
        user.setWord(message.getPersonId().toString());
        
        terms.add(user);
        
        String[] words = message.getText().split("\\s+", 0);
        for (String word : words)
        {
            word = word.replaceFirst("^[^\\w\\d]+", "")
                       .replaceFirst("[^\\w\\d]+$", "")
                       .replaceAll("'+", "'")
                       .toLowerCase();
            Term term = new Term();

            term.setId(message.getId());
            term.setDate(message.getDate());
            term.setFlag(Term.KEYWORD);
            term.setWord(word);
            
            terms.add(term);
        }
        return terms;
    }
}
