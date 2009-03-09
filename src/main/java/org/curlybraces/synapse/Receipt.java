package org.curlybraces.synapse;

// TODO Document.
public class Receipt
{
    // TODO Document.
    private final int code;
    
    // TODO Document.
    private final Verification verification;
    
    // TODO Document.
    public Receipt(int code, Verification verification)
    {
        this.code = code;
        this.verification = verification;
    }
    
    // TODO Document.
    public int getCode()
    {
        return code;
    }
    
    // TODO Document.
    public Verification getVerification()
    {
        return verification;
    }
}
