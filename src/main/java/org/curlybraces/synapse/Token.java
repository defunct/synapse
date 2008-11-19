package org.curlybraces.synapse;

import java.util.Date;
import java.util.UUID;

public class Token implements Comparable<Token>
{
    public final static short KEYWORD = 1;
    
    public final static short USER = 2;
    
    public final static short SHORTENED_URL = 3;
    
    public final static short FULL_URL = 4;

    private UUID messageId;
    
    private Date date;
    
    private short type;
    
    private String word;
    
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
    
    public void setType(short type)
    {
        this.type = type;
    }
    
    public short getType()
    {
        return type;
    }
    
    public void setWord(String word)
    {
        this.word = word;
    }
    
    public String getWord()
    {
        return word;
    }
    
    public String toString()
    {
        return "{ missive: " + messageId + ", type : " + type + ", word: " + word + "}"; 
    }
    
    public int compareTo(Token o)
    {
        int compare = getWord().compareTo(o.getWord());
        if (compare == 0)
        {
            compare = o.getDate().compareTo(getDate());
            if (compare == 0)
            {
                compare = getType() - o.getType();
                if (compare == 0)
                {
                    return getMessageId().compareTo(o.getMessageId());
                }
            }
            
        }
        return compare;
    }
    
    public String toTerm()
    {
        switch (getType())
        {
        case KEYWORD:
            return getWord();
        case USER:
            return '"' + getWord();
        case SHORTENED_URL:
            return ';' + getWord();
        default: 
            return ':' + getWord();
        }
    }
    
    @Override
    public boolean equals(Object object)
    {
        if (object instanceof Token)
        {
            Token term = (Token) object;
            return getWord().equals(term.getWord())
                && getDate().equals(term.getDate())
                && getType() == term.getType()
                && getMessageId() == term.getMessageId();
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 1;
        hash = hash * 37 + getMessageId().hashCode();
        hash = hash * 37 + getDate().hashCode();
        hash = hash * 37 + getType();
        hash = hash * 37 + getWord().hashCode();
        return hash;
    }
}
