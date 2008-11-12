package org.curlybraces.synapse;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.Date;
import java.util.UUID;

import org.curlybraces.synapse.terms.Term;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class TermParseTest
{
    private final static String ID = "7b2fb945-c913-48aa-ad25-346e27c2064b";
    
    private final static Date DATE = new Date(1226453280480L);
    
    private final static String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
        "<status>" +
            "<id>7b2fb945-c913-48aa-ad25-346e27c2064b</id>" +
            "<date>2008-11-12T01:28:00.48Z</date>" +
            "<flag>1</flag>" +
            "<word>Obama</word>" +
        "</status>";

    @Test public void parse()
    {
        Term term = new Term(UUID.fromString(ID), DATE, Term.KEYWORD, "Obama");
        assertEquals(term.getId().toString(), ID);
        assertEquals(term.getDate(), DATE);
        assertEquals(term.getFlag(), Term.KEYWORD);
        assertEquals(term.getWord(), "Obama");
    }
    
    @Test public void setters()
    {
        Term term = new Term();
        term.setId(ID);
        term.setDate(DATE);
        term.setFlag(Term.KEYWORD);
        term.setWord("Obama");
        assertEquals(term.getId().toString(), ID);
        assertEquals(term.getDate(), DATE);
        assertEquals(term.getFlag(), Term.KEYWORD);
        assertEquals(term.getWord(), "Obama");
    }
    
    @Test public void marshall() throws Exception
    {
        IBindingFactory bfact = BindingDirectory.getFactory(Term.class);
        IMarshallingContext m = bfact.createMarshallingContext();
        Term term = new Term(UUID.fromString(ID), DATE, Term.KEYWORD, "Obama");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        m.marshalDocument(term, "UTF-8", null, out);
        assertEquals(out.toString("UTF-8"), XML);
    }
    
    @Test public void unmarshall() throws Exception
    {
        IBindingFactory bfact = BindingDirectory.getFactory(Term.class);
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
        Term term = (Term) uctx.unmarshalDocument(new StringReader(XML));
        assertEquals(term.getId().toString(), ID);
        assertEquals(term.getDate(), DATE);
        assertEquals(term.getFlag(), Term.KEYWORD);
        assertEquals(term.getWord(), "Obama");
    }
}