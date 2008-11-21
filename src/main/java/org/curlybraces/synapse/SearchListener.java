package org.curlybraces.synapse;

import java.util.UUID;

public class SearchListener implements MessageCallback, ProfileCallback
{
    private final NodeListener listener;
    
    private final Node node;
    
    private final MatchAll search;
    
    private Message message;
    
    public SearchListener(Node node, MatchAll search, NodeListener listener)
    {
        this.node = node;
        this.search = search;
        this.listener = listener;
    }
    
    private void notifyListeners(Profile profile)
    {
        this.listener.found(search, message, profile);
        for (NodeListener listener : node.listeners())
        {
            listener.found(search, message, profile);
        }
    }
    
    public void run(Message message)
    {
        if (message == null)
        {
            notifyListeners(null);
        }
        else
        {
            this.message = message;
            UUID callbackId = node.newUUID();
            node.getProfileCallbacks().put(callbackId, this);
            Synapse synapse = new Synapse(
                    new RouteProfile(node.newStamp(), message.getProfileId()),
                    new GetProfile(node.newStamp(), message.getProfileId(), node.getURL(), callbackId)
                    );
            new NodeExecutor(node, synapse).execute();
        }
    }
    
    public void run(Profile profile)
    {
        notifyListeners(profile);
    }
}
