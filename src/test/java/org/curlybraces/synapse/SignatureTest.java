package org.curlybraces.synapse;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import org.testng.annotations.Test;

public class SignatureTest
{
    private byte[] sign(Command command, PrivateKey prvKey, String sigAlg)
            throws Exception
    {
        Signature sig = Signature.getInstance(sigAlg);
        sig.initSign(prvKey);
        byte[] dataBytes = command.getSignatureArray();
        sig.update(dataBytes, 0, dataBytes.length);
        return sig.sign();
    }

    private boolean verify(Command command, PublicKey pubKey,
            String sigAlg, byte[] sigbytes) throws Exception
    {
        Signature sig = Signature.getInstance(sigAlg);
        sig.initVerify(pubKey);
        byte[] dataBytes = command.getSignatureArray();
        sig.update(dataBytes, 0, dataBytes.length);
        return sig.verify(sigbytes);
    }

    @Test public void main() throws Exception
    {
        // Generate a key-pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        kpg.initialize(512); // 512 is the keysize.
        KeyPair kp = kpg.generateKeyPair();
        PublicKey pubk = kp.getPublic();
        PrivateKey prvk = kp.getPrivate();

        Echo echo = new Echo("Hello, World!");
        
        byte[] sigbytes = sign(echo, prvk, "SHAwithDSA");

        String hex = new BigInteger(sigbytes).toString(16);
        if  (hex.length() % 2 != 0)
        {
            hex = "0" + hex;
        }
        
        System.out.println("Signature(in hex):: f" + hex);

        boolean result = verify(echo, pubk, "SHAwithDSA", sigbytes);
        System.out.println("Signature Verification Result = " + result);
    }
}
