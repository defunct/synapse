package org.curlybraces.synapse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Archive
{
    private final UUID personId;
    
    private final Map<UUID, Message> mapOfMissives;
    
    public Archive(UUID personId)
    {
        this.personId = personId;
        this.mapOfMissives = new HashMap<UUID, Message>();
    }
    
    public UUID getPersonId()
    {
        return personId;
    }

    public void add(Message missive)
    {
        mapOfMissives.put(missive.getId(), missive);
    }
    
    public Message get(UUID missiveId)
    {
        return mapOfMissives.get(missiveId);
    }
}
