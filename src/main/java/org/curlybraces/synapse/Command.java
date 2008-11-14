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

    public abstract void execute(Node node, Synapse synapse);
}
