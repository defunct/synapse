package org.curlybraces.synapse;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * An instruction performed by the network and the history of its execution.
 *
 * @author Alan Gutierrez
 */
public class Synapse
{
    // TODO Document.
    private UUID id;
    
    // TODO Document.
    private List<URL> visited = new ArrayList<URL>();
    
    // TODO Document.
    private List<Command> executed = new ArrayList<Command>();

    // TODO Document.
    private LinkedList<Command> commands = new LinkedList<Command>();
    
    // TODO Document.
    public Synapse()
    {
    }
    
    // TODO Document.
    public Synapse(Command... commands)
    {
        push(commands);
        this.id = UUID.randomUUID();
    }
    
    // TODO Document.
    public UUID getId()
    {
        return id;
    }
    
    // TODO Document.
    public void shift(Command... inserts)
    {
        for (int i = 0; i < inserts.length; i++)
        {
            commands.addFirst(inserts[i]);
        }
    }
    
    // TODO Document.
    public void push(Command... appends)
    {
        for (int i = 0; i < appends.length; i++)
        {
            commands.addLast(appends[i]);
        }
    }
    
    // TODO Document.
    public void execute(Node node, SynapseQueue queue)
    {
        Command command = commands.removeFirst();
        command.execute(node, queue, this);
        executed.add(command);
        visited.add(node.getURL());
    }
}
