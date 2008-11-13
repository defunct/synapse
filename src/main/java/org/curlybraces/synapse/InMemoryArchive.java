package org.curlybraces.synapse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryArchive implements Archive
{
    private final UUID personId;
    
    private final Map<UUID, Missive> mapOfMissives;
    
    public InMemoryArchive(UUID personId)
    {
        this.personId = personId;
        this.mapOfMissives = new HashMap<UUID, Missive>();
    }
    
    public UUID getPersonId()
    {
        return personId;
    }

    public void add(Missive missive)
    {
        mapOfMissives.put(missive.getId(), missive);
    }
    
    public Missive get(UUID missiveId)
    {
        return mapOfMissives.get(missiveId);
    }
    
    public final static class Builder implements Archive.Builder
    {
        private UUID personId;
        
        public void setPersonId(UUID personId)
        {
            this.personId = personId;
        }
        
        public Archive newArchive()
        {
            return new InMemoryArchive(personId);
        }
    }
}
