package org.curlybraces.synapse.terms;

import java.util.Date;
import java.util.UUID;

public class Term
{
    public final static short KEYWORD = 1;
    
    public final static short USER = 2;
    
    public final static short SHORTENED_URL = 3;
    
    public final static short FULL_URL = 4;

    private UUID id;
    
    private Date date;
    
    private short flag;
    
    private String word;
    
    public Term()
    {
    }
    
    public Term(UUID id, Date date, short flag, String word)
    {
        this.id = id;
        this.date = date;
        this.flag = flag; 
        this.word = word;
    }
    
    public UUID getId()
    {
        return id;
    }
    
    public void setId(UUID id)
    {
        this.id = id;
    }
    
    public void setId(String id)
    {
        setId(UUID.fromString(id)); 
    }
    
    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }
    
    public void setFlag(short flag)
    {
        this.flag = flag;
    }
    
    public short getFlag()
    {
        return flag;
    }
    
    public void setWord(String word)
    {
        this.word = word;
    }
    
    public String getWord()
    {
        return word;
    }
}
