package org.curlybraces.synapse;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Base type for all commands that are exchanged between nodes over the wire
 * within synapses.
 * 
 * @author Alan Gutierrez
 */
public abstract class Command
{
    /**
     * This stamp is used so that there's something to bite into when a node
     * signs a command.
     */
    private Stamp stamp;
   
    /**
     * Default constructor used by serialization libraries.
     */
    public Command()
    {
    }

    /**
     * Create a new command with the specified stamp that will uniquely identify
     * the command.
     * 
     * @param stamp
     *            A stamp.
     */
    protected Command(Stamp stamp)
    {
        this.stamp = stamp;
    }
    
    public byte[] getSignatureArray()
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try
        {
            DataOutputStream data = new DataOutputStream(out);
            String className = getClass().getCanonicalName();
            for (int i = 0; i < className.length(); i++)
            {
                data.writeChar(className.charAt(i));
            }
            String id = stamp.getId().toString();
            for (int i = 0; i < id.length(); i++)
            {
                data.writeChar(id.charAt(i));
            }
            data.writeLong(stamp.getDate().getTime());
        }
        catch (IOException e)
        {
        }
        return out.toByteArray();
    }
    
    protected void addToSignature(DataOutputStream data)
    {
    }

    /**
     * Super classes implement this method to take a specific action based on
     * the type of command.
     * 
     * @param node
     *            The local node.
     * @param queue
     *            A queue of synapses to be executed by the local node.
     * @param synapse
     *            The currently executing synapse.
     */
    public abstract void execute(Node node, SynapseQueue queue, Synapse synapse);
}
