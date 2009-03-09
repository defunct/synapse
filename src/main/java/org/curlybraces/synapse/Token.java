package org.curlybraces.synapse;

import java.util.Date;
import java.util.UUID;

// TODO Document.
public class Token
{
    // TODO Document.
    private UUID messageId;
    
    // TODO Document.
    private Date date;
    
    // TODO Document.
    private Term term;
       
    // TODO Document.
    public Token()
    {
    }

    // TODO Document.
    public UUID getMessageId()
    {
        return messageId;
    }
    
    // TODO Document.
    public void setMessageId(UUID id)
    {
        this.messageId = id;
    }
    
    // TODO Document.
    public void setId(String id)
    {
        setMessageId(UUID.fromString(id)); 
    }
    
    // TODO Document.
    public Date getDate()
    {
        return date;
    }

    // TODO Document.
    public void setDate(Date date)
    {
        this.date = date;
    }
    
    // TODO Document.
    public void setTerm(Term term)
    {
        this.term = term;
    }
    
    // TODO Document.
    public Term getTerm()
    {
        return term;
    }
    
    // TODO Document.
    public String toString()
    {
        return "{ missive: " + messageId + ", term : " + term +  "}"; 
    }
    
    // TODO Document.
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
    
    // TODO Document.
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
