package org.curlybraces.synapse;

import static org.testng.Assert.assertEquals;

import java.util.Date;
import java.util.UUID;

import org.testng.annotations.Test;

public class MessageTest
{
    private final static String ID = "7b2fb945-c913-48aa-ad25-346e27c2064b";
    
    private final static Date DATE = new Date(1226453280480L);
    
    private final static String PERSON_ID = "4d7101d0-ccdf-4599-8132-4fb81158ace3";
    
    @Test public void setters()
    {
        Message message = new Message();
        
        System.out.println(UUID.randomUUID());
        
        message.setId(ID);
        message.setDate(DATE);
        message.setText("This is a test.");
        message.setProfileId(PERSON_ID);
        
        assertEquals(message.getId().toString(), ID);
        assertEquals(message.getDate(), DATE);
        assertEquals(message.getText(), "This is a test.");
        assertEquals(message.getProfileId().toString(), PERSON_ID);
    }
}
