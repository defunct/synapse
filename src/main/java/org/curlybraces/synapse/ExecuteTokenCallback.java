package org.curlybraces.synapse;

import java.util.List;
import java.util.UUID;

// TODO Document.
public class ExecuteTokenCallback extends Command
{
    // TODO Document.
    private UUID callbackId;
    
    // TODO Document.
    private Token[] tokens;
    
    // TODO Document.
    public ExecuteTokenCallback()
    {
    }
    
    // TODO Document.
    public ExecuteTokenCallback(Stamp stamp, UUID callbackId, List<Token> listOfTokens)
    {
        super(stamp);
        this.callbackId = callbackId;
        this.tokens = listOfTokens.toArray(new Token[listOfTokens.size()]);
    }
    
    // TODO Document.
    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        TokenCallback callback = node.getTokenCallbacks().callback(callbackId);
        if (callback != null)
        {
            callback.run(tokens);
        }
    }
}
