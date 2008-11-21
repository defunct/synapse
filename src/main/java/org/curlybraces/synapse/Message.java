package org.curlybraces.synapse;

import java.util.Date;
import java.util.UUID;

public class Message
{
    private UUID id;
    
    private UUID profileId;
    
    private Date date;
    
    private String text;

    public Message()
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
    
    public void setProfileId(UUID profileId)
    {
        this.profileId = profileId;
    }
    
    public void setProfileId(String profileId)
    {
        setProfileId(UUID.fromString(profileId));
    }
    
    public UUID getProfileId()
    {
        return profileId;
    }
    
    public void setDate(Date date)
    {
        this.date = date;
    }
    
    public Date getDate()
    {
        return date;
    }
    
    public void setText(String message)
    {
        this.text = message;
    }
    
    public String getText()
    {
        return text;
    }
}
