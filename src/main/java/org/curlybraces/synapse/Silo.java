package org.curlybraces.synapse;

import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

public interface Silo
{
    public UUID getId();
    
    public UUID getPreviousId();
    
    public Iterator<Term> equalToOrGreaterThan(String word, Date date);
    
    public boolean add(Term term);
    
    public int size();
    
    public boolean isFull();
    
    public interface Builder
    {
        public void setPreviousId(UUID previousId);
        
        public Silo newSilo();
    }
}
