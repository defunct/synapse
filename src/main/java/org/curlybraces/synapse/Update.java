package org.curlybraces.synapse;

import java.util.Date;
import java.util.UUID;

public class Update extends Command
{
    private Missive missive;
    
    public Update()
    {
    }

    public Update(Missive missive)
    {
        super(UUID.randomUUID(), new Date());
        this.missive = missive;
    }
    
    public void execute(Node node)
    {
        Archive archive = node.getArchiveManager().get(missive.getPersonId());
        archive.add(missive);
        for (SynapseListener listener : node.listeners())
        {
            listener.update(missive);
        }
    }
}
