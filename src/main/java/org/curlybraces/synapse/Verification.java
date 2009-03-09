package org.curlybraces.synapse;

// TODO Document.
public class Verification
{
    // TODO Document.
    private Executor executor;
    
    // TODO Document.
    private String message;
    
    // TODO Document.
    public Verification()
    {
        this.executor = new FailureExecutor();
    }
    
    // TODO Document.
    public Verification(Executor executor, String message)
    {
        this.executor = executor;
        this.message = message;
    }
    
    // TODO Document.
    public Executor getExecutor()
    {
        return executor;
    }
    
    // TODO Document.
    public String getMessage()
    {
        return message;
    }
}
