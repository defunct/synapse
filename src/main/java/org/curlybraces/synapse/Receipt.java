package org.curlybraces.synapse;

public class Receipt
{
    private final int code;
    
    private final Verification verification;
    
    public Receipt(int code, Verification verification)
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
