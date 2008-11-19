package org.curlybraces.synapse;

import java.util.UUID;

public class Profile
{
    public UUID id;
    
    public String name;
    
    public Profile()
    {
    }
    
    public Profile(UUID id)
    {
        this.id = id;
    }
    
    public UUID getId()
    {
        return id;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
}
