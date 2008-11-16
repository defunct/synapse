package org.curlybraces.synapse;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Update extends Command
{
    private Message message;

    public Update()
    {
    }

    public Update(Message message)
    {
        super(UUID.randomUUID(), new Date());
        this.message = message;
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
    public void execute(final Node node, Synapse synapse)
    {
        Archive archive = node.getArchiveManager().get(message.getPersonId());
        if (archive != null)
        {
            archive.add(message);
        }

        Tokenizer tokenizer = new Tokenizer();
        List<Term> terms = tokenizer.tokenize(message); 
        
        for (Term term : terms)
        {
            term.getClass();
        }
        
        UUID callbackId = node.newCallback(new Runnable()
        {
            public void run()
            {
                for (SynapseListener listener : node.listeners())
                {
                    listener.update(message);
                }
            }
        });

        Synapse inject = new Synapse(new RouteMessageSynapse(message.getId()),
                                     new InjectMessage(message),
                                     new Callback(node.getURL()),
                                     new ExecuteCallback(callbackId));
        node.sendCommand(node.getURL(), inject);
    }
}
