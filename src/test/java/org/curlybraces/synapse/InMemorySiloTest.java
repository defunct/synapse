package org.curlybraces.synapse;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

public class InMemorySiloTest
{
    private final static String ID = "7b2fb945-c913-48aa-ad25-346e27c2064b";
    
    private final static Date DATE = new Date(1226453280480L);

    @Test public void create()
    {
        Silo.Builder newSilo = new InMemorySilo.Builder(3);
        Silo silo = newSilo.newSilo();

        Missive missive = new Missive();
        
        missive.setId(ID);
        missive.setDate(DATE);
        missive.setMessage("This is a test.");
        missive.setPersonId(ID);

        Tokenizer tokenizer = new Tokenizer();
        List<Term> terms = tokenizer.tokenize(missive);
        assertTrue(silo.add(terms.get(0)));
        assertTrue(silo.add(terms.get(1)));
        assertTrue(silo.add(terms.get(2)));
        assertFalse(silo.add(terms.get(3)));
    }
}
