package org.curlybraces.synapse;

import java.util.Date;
import java.util.UUID;

public class Stamp
{
    private UUID id;
    
    private Date date;
    
    public Stamp()
    {
    }
    
    public Stamp(UUID id, Date date)
    {
        this.id = id;
        this.date = date;
    }
    
    public UUID getId()
    {
        return id;
    }
    
    public Date getDate()
    {
        return date;
    }
}
