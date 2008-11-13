package org.curlybraces.synapse;

import java.util.Date;
import java.util.UUID;

public class Term implements Comparable<Term>
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
    
    public String toString()
    {
        return "{ missive: " + id + ", type : " + flag + ", word: " + word + "}"; 
    }
    
    public int compareTo(Term o)
    {
        int compare = getWord().compareTo(o.getWord());
        if (compare == 0)
        {
            compare = o.getDate().compareTo(getDate());
            if (compare == 0)
            {
                compare = getFlag() - o.getFlag();
                if (compare == 0)
                {
                    return getId().compareTo(o.getId());
                }
            }
            
        }
        return compare;
    }
    
    @Override
    public boolean equals(Object object)
    {
        if (object instanceof Term)
        {
            Term term = (Term) object;
            return getWord().equals(term.getWord())
                && getDate().equals(term.getDate())
                && getFlag() == term.getFlag()
                && getId() == term.getId();
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 1;
        hash = hash * 37 + getId().hashCode();
        hash = hash * 37 + getDate().hashCode();
        hash = hash * 37 + getFlag();
        hash = hash * 37 + getWord().hashCode();
        return hash;
    }
}
