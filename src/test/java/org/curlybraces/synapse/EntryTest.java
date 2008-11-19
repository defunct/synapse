package org.curlybraces.synapse;

import java.util.Date;
import java.util.UUID;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class EntryTest
{
    private final static Date DATE = new Date(1226453280480L);
    
    private final static UUID ID = UUID.fromString("7b2fb945-c913-48aa-ad25-346e27c2064b");
    
    private UUID getId(int inc)
    {
        return new UUID(ID.getMostSignificantBits(), ID.getLeastSignificantBits() + inc);
    }
    
    private Date getDate(int inc)
    {
        return new Date(DATE.getTime() + inc);
    }

    @Test public void entry()
    {
        int id = 0;
        Entry entry = new Entry("word", Token.KEYWORD);
        for (int i = 0; i < 256; i++)
        {
            entry.add(getDate(i), getId(id++));
        }
        assertEquals(entry.get(getDate(34)).get(0).getMessageId(), getId(34));
        assertEquals(entry.get(getDate(-1)).size(), 0);
        entry.add(getDate(0), getId(id++));
        entry.add(getDate(1), getId(id++));
        entry.add(getDate(-1), getId(id++));
        entry.add(getDate(86), getId(id++));
        entry.add(getDate(86), getId(id++));
        entry.add(getDate(86), getId(id));
        assertEquals(entry.get(getDate(86)).size(), 4);
        entry.add(getDate(86), getId(id++));
        assertEquals(entry.get(getDate(86)).size(), 4);
        entry.add(getDate(255), getId(id++));
        entry.add(getDate(255), getId(id++));
    }
}
