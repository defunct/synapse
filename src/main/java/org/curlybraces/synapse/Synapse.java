package org.curlybraces.synapse;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Collections;

public class Synapse
{
    private ArrayList<Command> commands = new ArrayList<Command>();
    
    public Synapse()
    {
    }
    
    public Synapse(Command command)
    {
        add(command);
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
