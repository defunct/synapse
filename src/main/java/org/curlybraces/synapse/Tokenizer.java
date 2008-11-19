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
        user.setType(Token.USER);
        user.setWord(message.getPersonId().toString());
        
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
            token.setType(Token.KEYWORD);
            token.setWord(word);
            
            terms.add(token);
        }
        return terms;
    }
}
