package org.curlybraces.synapse;

import static org.testng.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.Date;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.testng.annotations.Test;

public class TokenTest
{
    private final static String ID = "7b2fb945-c913-48aa-ad25-346e27c2064b";
    
    private final static Date DATE = new Date(1226453280480L);
    
    private final static String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
        "<token>" +
            "<id>7b2fb945-c913-48aa-ad25-346e27c2064b</id>" +
            "<date>2008-11-12T01:28:00.48Z</date>" +
            "<flag>1</flag>" +
            "<word>Obama</word>" +
        "</token>";

    @Test public void setters()
    {
        Token token = new Token();
        token.setId(ID);
        token.setDate(DATE);
        token.setType(Token.KEYWORD);
        token.setWord("Obama");
        assertEquals(token.getMessageId().toString(), ID);
        assertEquals(token.getDate(), DATE);
        assertEquals(token.getType(), Token.KEYWORD);
        assertEquals(token.getWord(), "Obama");
    }
    
    @Test public void marshall() throws Exception
    {
        IBindingFactory bfact = BindingDirectory.getFactory(Token.class);
        IMarshallingContext m = bfact.createMarshallingContext();
        
        Token token = new Token();

        token.setId(ID);
        token.setDate(DATE);
        token.setType(Token.KEYWORD);
        token.setWord("Obama");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        m.marshalDocument(token, "UTF-8", null, out);
        assertEquals(out.toString("UTF-8"), XML);
    }
    
    @Test public void unmarshall() throws Exception
    {
        IBindingFactory bfact = BindingDirectory.getFactory(Token.class);
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
        Token term = (Token) uctx.unmarshalDocument(new StringReader(XML));
        assertEquals(term.getMessageId().toString(), ID);
        assertEquals(term.getDate(), DATE);
        assertEquals(term.getType(), Token.KEYWORD);
        assertEquals(term.getWord(), "Obama");
    }
}