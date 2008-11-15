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

    /**
     * Add a new message to the synapse network.
     * <p>
     * If this node is responsible for archiving the missives of the missive
     * user, the missive will be stored in the archive attached to the node.
     * <p>
     * The missive will be injected into the network, where it will be routed to
     * storage based on the UUID of the missive.
     * 
     * @param node
     *            The node in the network.
     * @param synapse
     *            The currently firing synapse.
     */
    public void execute(Node node, Synapse synapse)
    {
        Archive archive = node.getArchiveManager().get(missive.getPersonId());
        if (archive != null)
        {
            archive.add(missive);
        }
        Synapse inject = new Synapse(new RouteMessageSynapse(missive.getId()),
                                     new InjectMessage(missive));
        node.getLocator().sendCommand(inject);
    }
}
