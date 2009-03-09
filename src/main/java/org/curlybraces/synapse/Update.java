package org.curlybraces.synapse;

import java.util.List;
import java.util.UUID;

// TODO Document.
public class Update extends Command
{
    // TODO Document.
    private Message message;

    // TODO Document.
    public Update()
    {
    }

    // TODO Document.
    public Update(Stamp stamp, Message message)
    {
        super(stamp);
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
        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.tokenize(message); 
        
        UpdateListener listener = new UpdateListener(node, message, tokens.size() + 1);
        for (Token token : tokens)
        {
            UUID callbackId = node.newUUID();
            node.getVoidCallbacks().put(callbackId, new UpdateCallback(listener));
            Synapse inject = new Synapse(
                    new RouteToken(node.newStamp(), token.getTerm()),
                    new InjectToken(node.newStamp(), token),
                    new GoTo(node.newStamp(), node.getURL()),
                    new ExecuteCallback(node.newStamp(), callbackId));
            queue.enqueue(inject);
        }
        
        UUID callbackId = node.newUUID();
        node.getVoidCallbacks().put(callbackId, new UpdateCallback(listener));
        Synapse inject = new Synapse(
                new RouteMessage(node.newStamp(), message.getId()),
                new InjectMessage(node.newStamp(), message),
                new GoTo(node.newStamp(), node.getURL()),
                new ExecuteCallback(node.newStamp(), callbackId));
        queue.enqueue(inject);
    }
}
