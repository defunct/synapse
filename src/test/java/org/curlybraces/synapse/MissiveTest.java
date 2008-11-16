package org.curlybraces.synapse;

import static org.testng.Assert.assertEquals;

import java.util.Date;
import java.util.UUID;

import org.testng.annotations.Test;

public class MissiveTest
{
    private final static String ID = "7b2fb945-c913-48aa-ad25-346e27c2064b";
    
    private final static Date DATE = new Date(1226453280480L);
    
    private final static String PERSON_ID = "4d7101d0-ccdf-4599-8132-4fb81158ace3";
    
    @Test public void setters()
    {
        Missive missive = new Missive();
        
        System.out.println(UUID.randomUUID());
        
        missive.setId(ID);
        missive.setDate(DATE);
        missive.setText("This is a test.");
        missive.setPersonId(PERSON_ID);
        
        assertEquals(missive.getId().toString(), ID);
        assertEquals(missive.getDate(), DATE);
        assertEquals(missive.getText(), "This is a test.");
        assertEquals(missive.getPersonId().toString(), PERSON_ID);
    }
}
