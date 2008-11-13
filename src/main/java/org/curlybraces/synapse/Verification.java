package org.curlybraces.synapse;

public class Verification
{
    private Executor executor;
    
    private String message;
    
    public Verification()
    {
        this.executor = new FailureExecutor();
    }
    
    public Verification(Executor executor, String message)
    {
        this.executor = executor;
        this.message = message;
    }
    
    public Executor getExecutor()
    {
        return executor;
    }
    
    public String getMessage()
    {
        return message;
    }
}
