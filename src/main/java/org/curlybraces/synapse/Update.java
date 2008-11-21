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
     * @param queue
     *            A queue of synapses to be executed as part of this request.
     * @param synapse
     *            The currently firing synapse.
     */
    public void execute(final Node node, SynapseQueue queue, Synapse synapse)
    {
        Archive archive = node.getArchive(message.getProfileId());
        if (archive != null)
        {
            archive.add(message);
        }

        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.tokenize(message); 
        
        UpdateListener listener = new UpdateListener(node, message, tokens.size() + 1);
        for (Token token : tokens)
        {
            UUID callbackId = node.newCallback(new UpdateCallback(listener));
            Synapse inject = new Synapse(new RouteToken(token.toTerm()),
                                         new InjectToken(token),
                                         new Callback(node.getURL()),
                                         new ExecuteCallback(callbackId));
            queue.enqueue(inject);
        }
        
        UUID callbackId = node.newCallback(new UpdateCallback(listener));
        Synapse inject = new Synapse(new RouteMessage(message.getId()),
                                     new InjectMessage(message),
                                     new Callback(node.getURL()),
                                     new ExecuteCallback(callbackId));
        queue.enqueue(inject);
    }
}
