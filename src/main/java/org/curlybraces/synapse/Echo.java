package org.curlybraces.synapse;

import java.util.Date;
import java.util.UUID;

public class Echo extends Command
{
    private String message;
    
    public Echo()
    {
    }

    public Echo(String message)
    {
        super(UUID.randomUUID(), new Date());
        this.message = message;
    }

    public void execute(Node node)
    {
        System.out.println(message);
    }
}
