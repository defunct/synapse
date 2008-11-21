package org.curlybraces.synapse;

import java.util.List;
import java.util.UUID;

public class ExecuteTokenCallback extends Command
{
    private UUID callbackId;
    
    private Token[] tokens;
    
    public ExecuteTokenCallback()
    {
    }
    
    public ExecuteTokenCallback(Stamp stamp, UUID callbackId, List<Token> listOfTokens)
    {
        super(stamp);
        this.callbackId = callbackId;
        this.tokens = listOfTokens.toArray(new Token[listOfTokens.size()]);
    }
    
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
