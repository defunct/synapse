package org.curlybraces.synapse;

/**
 * Callback response to profile injection notifies node listeners that a profile
 * has been successfully injected into the network.
 * 
 * @author Alan Gutierrez
 */
public class SetProfileCallback implements Runnable
{
    /** The node. */
    private final Node node;
    
    /** The profile. */
    private final Profile profile;

    /**
     * Construct a callback that will notify the specified node that the
     * specified profile has been successfully injected into the network.
     * 
     * @param node
     *            The node.
     * @param profile
     *            The profile.
     */
    public SetProfileCallback(Node node, Profile profile)
    {
        this.node = node;
        this.profile = profile;
    }

    /**
     * Notify node listeners that a profile has been successfully injected into
     * the network.
     */
    public void run()
    {
        for (NodeListener listener : node.listeners())
        {
            listener.setProfile(profile);
        }
    }
}
