package org.curlybraces.synapse;

import java.util.Date;
import java.util.UUID;

public class Token
{
    private UUID messageId;
    
    private Date date;
    
    private Term term;
       
    public Token()
    {
    }

    public UUID getMessageId()
    {
        return messageId;
    }
    
    public void setMessageId(UUID id)
    {
        this.messageId = id;
    }
    
    public void setId(String id)
    {
        setMessageId(UUID.fromString(id)); 
    }
    
    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }
    
    public void setTerm(Term term)
    {
        this.term = term;
    }
    
    public Term getTerm()
    {
        return term;
    }
    
    public String toString()
    {
        return "{ missive: " + messageId + ", term : " + term +  "}"; 
    }
    
    
    @Override
    public boolean equals(Object object)
    {
        if (object instanceof Token)
        {
            Token token = (Token) object;
            return getTerm().equals(token.getTerm())
                && getDate().equals(token.getDate())
                && getMessageId() == token.getMessageId();
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 1;
        hash = hash * 37 + getMessageId().hashCode();
        hash = hash * 37 + getDate().hashCode();
        hash = hash * 37 + getTerm().hashCode();
        return hash;
    }
}
