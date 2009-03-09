package org.curlybraces.synapse;

import java.util.UUID;

// TODO Document.
public class SearchListener implements MessageCallback, ProfileCallback
{
    // TODO Document.
    private final NodeListener listener;
    
    // TODO Document.
    private final Node node;
    
    // TODO Document.
    private final MatchAll search;
    
    // TODO Document.
    private Message message;
    
    // TODO Document.
    public SearchListener(Node node, MatchAll search, NodeListener listener)
    {
        this.node = node;
        this.search = search;
        this.listener = listener;
    }
    
    // TODO Document.
    private void notifyListeners(Profile profile)
    {
        this.listener.found(search, message, profile);
        for (NodeListener listener : node.listeners())
        {
            listener.found(search, message, profile);
        }
    }
    
    // TODO Document.
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
    
    // TODO Document.
    public void run(Profile profile)
    {
        notifyListeners(profile);
    }
}
