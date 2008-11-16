package org.curlybraces.synapse;

import static org.testng.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

public class TokenizerTest
{
    private final static String ID = "7b2fb945-c913-48aa-ad25-346e27c2064b";

    private final static Date DATE = new Date(1226453280480L);

    @Test public void tokenize()
    {
        Message missive = new Message();
        
        missive.setId(ID);
        missive.setDate(DATE);
        missive.setText("This is a test.");
        missive.setPersonId(ID);
        
        Tokenizer tokenizer = new Tokenizer();
        List<Term> terms = tokenizer.tokenize(missive);
        
        assertEquals(terms.size(), 5);
        assertEquals(terms.get(0).getWord(), ID.toString());
        assertEquals(terms.get(1).getWord(), "this");
        assertEquals(terms.get(2).getWord(), "is");
        assertEquals(terms.get(3).getWord(), "a");
        assertEquals(terms.get(4).getWord(), "test");
    }
}
