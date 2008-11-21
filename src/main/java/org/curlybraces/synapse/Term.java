package org.curlybraces.synapse;

public class Term implements Comparable<Term>
{
    public final static Term MIN_TERM = new Term("", (short) 0);
    
    public final static Term MAX_TERM = new Term("", Short.MAX_VALUE);
    
    public final static short KEYWORD = 1;
    
    // FIXME Profile id.
    public final static short USER = 2;
    
    public final static short SHORTENED_URL = 3;
    
    public final static short FULL_URL = 4;
    
    private String word;
    
    private short type;
    
    public Term()
    {
    }
    
    public Term(String word, short type)
    {
        this.word = word;
        this.type = type;
    }
    
    public void setWord(String word)
    {
        this.word = word;
    }
    
    public String getWord()
    {
        return word;
    }
    
    public void setType(short type)
    {
        this.type = type;
    }
    
    public short getType()
    {
        return type;
    }
    
    public int compareTo(Term term)
    {
        int compare = type - term.type;
        if (compare == 0)
        {
            compare = word.compareTo(term.word);
        }
        return compare;
    }
    
    @Override
    public boolean equals(Object object)
    {
        if (object instanceof Term)
        {
            Term term = (Term) object;
            return word.equals(term.word) && type == term.type;
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 1;
        hash = hash * 37 + word.hashCode();
        hash = hash * 37 + type;
        return hash;
    }
    
    public String toString()
    {
        switch (getType())
        {
        case Term.KEYWORD:
            return getWord();
        case Term.USER:
            return '>' + getWord();
        case Term.SHORTENED_URL:
            return ';' + getWord();
        default: 
            return ':' + getWord();
        }
    }
}
