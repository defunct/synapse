package org.curlybraces.synapse;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * This class is not abstract, only because I couldn't see how to specify
 * an abstract class in JiBX.
 * @author Alan Gutierrez
 */
public abstract class Command
{
    /**
     * This stamp is used so that there's something to bite into when a node
     * signs a command.
     */
    private Stamp stamp;
    
    public Command()
    {
    }
    
    protected Command(Stamp stamp)
    {
        this.stamp = stamp;
    }

    /**
     * Return true if this command is the culmination of series of commands,
     * return false if it is a step toward resolving a command. When a terminal
     * command is reached, the {@link Synapse#execute(Node)} method will begin
     * the next series of execution by sending the command to the currently
     * active node.
     * 
     * FIXME Delete this.
     * 
     * @return True if this is a terminal command.
     */
    public boolean isTerminal()
    {
        return true;
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

    public abstract void execute(Node node, SynapseQueue queue, Synapse synapse);
}
