package org.curlybraces.synapse;

import java.util.Date;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

public class InMemorySilo implements Silo
{
    private final UUID id;
    
    private final UUID previousId;
    
    private final int capacity;
    
    private final SortedSet<Term> setOfTerms;
    
    private InMemorySilo(int capacity, UUID previousId)
    {
        this.id = UUID.randomUUID();
        this.previousId = previousId;
        this.capacity = capacity;
        this.setOfTerms = new TreeSet<Term>();
    }
    
    public UUID getId()
    {
        return id;
    }
    
    public UUID getPreviousId()
    {
        return previousId;
    }
    
    public Iterator<Term> equalToOrGreaterThan(String word, Date date)
    {
        Term term = new Term();
        term.setDate(date);
        term.setWord(word);
        return setOfTerms.tailSet(term).iterator();
    }
    
    public int size()
    {
        return setOfTerms.size();
    }
    
    public boolean add(Term term)
    {
        if (!isFull())
        {
            return setOfTerms.add(term);
        }
        return false;
    }
    
    public boolean isFull()
    {
        return setOfTerms.size() == capacity;
    }
    
    public final static class Builder implements Silo.Builder
    {
        private final int capacity;
        
        private UUID previousId;
        
        public Builder(int capacity)
        {
            this.capacity = capacity;
        }
        
        public void setPreviousId(UUID previousId)
        {
            this.previousId = previousId;
        }
        
        public Silo newSilo()
        {
            return new InMemorySilo(capacity, previousId);
        }
    }
}
