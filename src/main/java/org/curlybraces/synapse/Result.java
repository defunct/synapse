package org.curlybraces.synapse;

public class Result
{
    private final int code;
    
    private final Verification verification;
    
    public Result(int code, Verification verification)
    {
        this.code = code;
        this.verification = verification;
    }
    
    public int getCode()
    {
        return code;
    }
    
    public Verification getVerification()
    {
        return verification;
    }
}
