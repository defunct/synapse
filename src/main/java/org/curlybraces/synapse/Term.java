package org.curlybraces.synapse;

// TODO Document.
public class Term implements Comparable<Term>
{
    // TODO Document.
    public final static Term MIN_TERM = new Term("", (short) 0);
    
    // TODO Document.
    public final static Term MAX_TERM = new Term("", Short.MAX_VALUE);
    
    // TODO Document.
    public final static short KEYWORD = 1;
    
    // TODO Document.
    public final static short PROFILE_ID = 2;
    
    // TODO Document.
    public final static short SHORTENED_URL = 3;
    
    // TODO Document.
    public final static short FULL_URL = 4;
    
    // TODO Document.
    private String word;
    
    // TODO Document.
    private short type;
    
    // TODO Document.
    public Term()
    {
    }
    
    // TODO Document.
    public Term(String word, short type)
    {
        this.word = word;
        this.type = type;
    }
    
    // TODO Document.
    public void setWord(String word)
    {
        this.word = word;
    }
    
    // TODO Document.
    public String getWord()
    {
        return word;
    }
    
    // TODO Document.
    public void setType(short type)
    {
        this.type = type;
    }
    
    // TODO Document.
    public short getType()
    {
        return type;
    }
    
    // TODO Document.
    public int compareTo(Term term)
    {
        int compare = type - term.type;
        if (compare == 0)
        {
            compare = word.compareTo(term.word);
        }
        return compare;
    }
    
    // TODO Document.
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
    
    // TODO Document.
    @Override
    public int hashCode()
    {
        int hash = 1;
        hash = hash * 37 + word.hashCode();
        hash = hash * 37 + type;
        return hash;
    }
    
    // TODO Document.
    public String toString()
    {
        switch (getType())
        {
        case Term.KEYWORD:
            return getWord();
        case Term.PROFILE_ID:
            return '>' + getWord();
        case Term.SHORTENED_URL:
            return ';' + getWord();
        default: 
            return ':' + getWord();
        }
    }
}
