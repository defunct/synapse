package org.curlybraces.synapse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * An instruction performed by the network and the history of its execution.

 * @author Alan Gutierrez
 */
public class Synapse
{
    private UUID id;
    
    private List<Locator> visited = new ArrayList<Locator>();
    
    private List<Command> executed = new ArrayList<Command>();

    private LinkedList<Command> commands = new LinkedList<Command>();
    
    public Synapse()
    {
    }
    
    public Synapse(Command... commands)
    {
        push(commands);
        this.id = UUID.randomUUID();
    }
    
    public UUID getId()
    {
        return id;
    }
    
    public void shift(Command... inserts)
    {
        for (int i = 0; i < inserts.length; i++)
        {
            commands.addFirst(inserts[i]);
        }
    }
    
    public void push(Command... appends)
    {
        for (int i = 0; i < appends.length; i++)
        {
            commands.addLast(appends[i]);
        }
    }
    
    public void execute(Node node)
    {
        Command command = commands.removeFirst();
        executed.add(command);
        visited.add(node.getLocator());
        command.execute(node, this);
    }
}
