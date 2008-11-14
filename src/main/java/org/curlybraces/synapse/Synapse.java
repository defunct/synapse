package org.curlybraces.synapse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import java.util.Collections;

public class Synapse
{
    private UUID id;

    private ArrayList<Command> commands = new ArrayList<Command>();
    
    public Synapse()
    {
    }
    
    public Synapse(Command command)
    {
        add(command);
        this.id = UUID.randomUUID();
    }
    
    public UUID getId()
    {
        return id;
    }
    
    public void add(Command command)
    {
        commands.add(command);
    }
    
    public Collection<Command> commands()
    {
        return Collections.unmodifiableCollection(commands);
    }
}
