package org.curlybraces.synapse;

import java.net.URL;

public interface Contributor
{
    public URL getURL();
    
    public void setProfile(Profile profile);
    
    public void update(Message message);
    
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
