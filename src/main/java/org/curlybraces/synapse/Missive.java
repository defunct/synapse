package org.curlybraces.synapse;

import java.util.Date;
import java.util.UUID;

public class Missive
{
    private UUID id;
    
    private UUID personId;
    
    private Date date;
    
    private String message;

    public Missive()
    {
    }

    public void setId(UUID id)
    {
        this.id = id;
    }
    
    public void setId(String id)
    {
        setId(UUID.fromString(id));
    }
    
    public UUID getId()
    {
        return id;
    }
    
    public void setPersonId(UUID personId)
    {
        this.personId = personId;
    }
    
    public void setPersonId(String personId)
    {
        setPersonId(UUID.fromString(personId));
    }
    
    public UUID getPersonId()
    {
        return personId;
    }
    
    public void setDate(Date date)
    {
        this.date = date;
    }
    
    public Date getDate()
    {
        return date;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public String getMessage()
    {
        return message;
    }
}
