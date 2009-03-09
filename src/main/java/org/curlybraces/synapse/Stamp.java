package org.curlybraces.synapse;

import java.util.Date;
import java.util.UUID;

// TODO Document.
public class Stamp
{
    // TODO Document.
    private UUID id;
    
    // TODO Document.
    private Date date;
    
    // TODO Document.
    public Stamp()
    {
    }
    
    // TODO Document.
    public Stamp(UUID id, Date date)
    {
        this.id = id;
        this.date = date;
    }
    
    // TODO Document.
    public UUID getId()
    {
        return id;
    }
    
    // TODO Document.
    public Date getDate()
    {
        return date;
    }
}
