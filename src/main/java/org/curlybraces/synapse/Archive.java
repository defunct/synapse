package org.curlybraces.synapse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * Archive manages term entry skip list.
 * </p>
 * 
 * @author Alan Gutierrez
 */
public class Archive
{
    // TODO Document.
    private final UUID personId;
    
    // TODO Document.
    private final Map<UUID, Message> mapOfMissives;
    
    // TODO Document.
    public Archive(UUID personId)
    {
        this.personId = personId;
        this.mapOfMissives = new HashMap<UUID, Message>();
    }
    
    // TODO Document.
    public UUID getPersonId()
    {
        return personId;
    }

    // TODO Document.
    public void add(Message missive)
    {
        mapOfMissives.put(missive.getId(), missive);
    }
    
    // TODO Document.
    public Message get(UUID missiveId)
    {
        return mapOfMissives.get(missiveId);
    }
}
