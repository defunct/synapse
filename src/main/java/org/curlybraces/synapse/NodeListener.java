package org.curlybraces.synapse;

/**
 * The listener interface for receiving notification of successfully completed
 * network events and network errors.
 * 
 * @author Alan Gutierrez
 */
public class NodeListener
{
    /**
     * Called when a profile has been injected into the network by this node.
     * 
     * @param profile
     *            The profile injected by this node.
     */
    public void setProfile(Profile profile)
    {
    }

    /**
     * Called when a message has been successfully injected into the network by
     * this node.
     * 
     * @param message
     *            The message injected by the node.
     */
    public void update(Message message)
    {
    }
}
