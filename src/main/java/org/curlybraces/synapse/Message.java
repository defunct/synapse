package org.curlybraces.synapse;

import java.util.Date;
import java.util.UUID;

// TODO Document.
public class Message
{
    // TODO Document.
    private UUID id;
    
    // TODO Document.
    private UUID profileId;
    
    // TODO Document.
    private Date date;
    
    // TODO Document.
    private String text;

    // TODO Document.
    public Message()
    {
    }

    // TODO Document.
    public void setId(UUID id)
    {
        this.id = id;
    }
    
    // TODO Document.
    public void setId(String id)
    {
        setId(UUID.fromString(id));
    }
    
    // TODO Document.
    public UUID getId()
    {
        return id;
    }
    
    // TODO Document.
    public void setProfileId(UUID profileId)
    {
        this.profileId = profileId;
    }
    
    // TODO Document.
    public void setProfileId(String profileId)
    {
        setProfileId(UUID.fromString(profileId));
    }
    
    // TODO Document.
    public UUID getProfileId()
    {
        return profileId;
    }
    
    // TODO Document.
    public void setDate(Date date)
    {
        this.date = date;
    }
    
    // TODO Document.
    public Date getDate()
    {
        return date;
    }
    
    // TODO Document.
    public void setText(String message)
    {
        this.text = message;
    }
    
    // TODO Document.
    public String getText()
    {
        return text;
    }
}
