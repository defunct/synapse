package org.curlybraces.synapse;

import static org.testng.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

public class TokenizerTest
{
    private final static String ID = "7b2fb945-c913-48aa-ad25-346e27c2064b";

    private final static Date DATE = new Date(1226453280480L);

    private final static String PERSON_ID = "4d7101d0-ccdf-4599-8132-4fb81158ace3";

    @Test public void tokenize()
    {
        Message message = new Message();
        
        message.setId(ID);
        message.setDate(DATE);
        message.setText("This is a test.");
        message.setProfileId(PERSON_ID);
        
        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.tokenize(message);
        
        assertEquals(tokens.size(), 5);
        assertEquals(tokens.get(0).getTerm().getWord(), PERSON_ID.toString());
        assertEquals(tokens.get(1).getTerm().getWord(), "this");
        assertEquals(tokens.get(2).getTerm().getWord(), "is");
        assertEquals(tokens.get(3).getTerm().getWord(), "a");
        assertEquals(tokens.get(4).getTerm().getWord(), "test");
    }
}
