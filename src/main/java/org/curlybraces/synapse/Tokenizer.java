package org.curlybraces.synapse;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer
{
    public List<Token> tokenize(Message message)
    {
        List<Token> terms = new ArrayList<Token>();

        Token user = new Token();
        user.setMessageId(message.getId());
        user.setDate(message.getDate());
        user.setTerm(new Term(message.getProfileId().toString(), Term.PROFILE_ID));
        
        terms.add(user);
        
        String[] words = message.getText().split("\\s+", 0);
        for (String word : words)
        {
            word = word.replaceFirst("^[^\\w\\d]+", "")
                       .replaceFirst("[^\\w\\d]+$", "")
                       .replaceAll("'+", "'")
                       .toLowerCase();
            Token token = new Token();

            token.setMessageId(message.getId());
            token.setDate(message.getDate());
            token.setTerm(new Term(word, Term.KEYWORD));
            
            terms.add(token);
        }
        return terms;
    }
}
