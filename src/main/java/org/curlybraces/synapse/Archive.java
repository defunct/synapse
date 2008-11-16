package org.curlybraces.synapse;

import java.util.UUID;

public interface Archive
{
    public UUID getPersonId();

    public void add(Message missive);
    
    public Message get(UUID missiveId);
    
    public interface Builder
    {
        public void setPersonId(UUID personId);
        
        public Archive newArchive();
    }
}
