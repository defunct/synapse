package org.curlybraces.synapse;

import java.net.URL;

// TODO Document.
public interface Contributor
{
    // TODO Document.
    public URL getURL();
    
    // TODO Document.
    public void setProfile(Profile profile);
    
    // TODO Document.
    public void update(Message message);
    
    // TODO Document.
    public void search(MatchAll search, NodeListener listener);

    /**
     * Add a synapse listener to the node.
     * 
     * @param listener
     *            A synapse listener.
     */
    public void addListener(NodeListener listener);

    /**
     * Remove a synapse listener from the node. A synapse listener is identified
     * by its object identity.
     * 
     * @param listener
     *            A synapse listener to remove.
     */
    public void removeListener(NodeListener listener);
}
