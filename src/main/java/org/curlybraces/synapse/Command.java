package org.curlybraces.synapse;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public abstract class Command
{
    private UUID id;
    
    private Date date;
    
    public Command()
    {
    }
    
    protected Command(UUID id, Date date)
    {
        this.id = id;
        this.date = date;
    }

    /**
     * Return true if this command is the culmination of series of commands,
     * return false if it is a step toward resolving a command. When a terminal
     * command is reached, the {@link Synapse#execute(Node)} method will begin
     * the next series of execution by sending the command to the currently
     * active node.
     * 
     * @return True if this is a terminal command.
     */
    public boolean isTerminal()
    {
        return true;
    }
    
    public UUID getId()
    {
        return id;
    }
    
    public Date getDate()
    {
        return date;
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
            String id = getId().toString();
            for (int i = 0; i < id.length(); i++)
            {
                data.writeChar(id.charAt(i));
            }
            data.writeLong(date.getTime());
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
