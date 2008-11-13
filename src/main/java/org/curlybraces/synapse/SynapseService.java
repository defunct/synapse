package org.curlybraces.synapse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

public class SynapseService
{
    private final Node node;

    public SynapseService(Node node)
    {
        this.node = node;
    }
    
    public Node getNode()
    {
        return node;
    }
    
    public void post(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        try
        {
            IBindingFactory bfact = BindingDirectory.getFactory(Synapse.class);
            IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
            Synapse synapse = (Synapse) uctx.unmarshalDocument(request.getInputStream(), "UTF-8");
            Verification verification = node.verify(synapse);

            IBindingFactory verifications = BindingDirectory.getFactory(Verification.class);
            IMarshallingContext m = verifications.createMarshallingContext();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            m.marshalDocument(verification, "UTF-8", null, out);
            
            byte[] bytes = out.toByteArray();
            response.setContentType("text/xml;charset=utf-8");
            response.setContentLength(bytes.length);
            response.getOutputStream().write(bytes);
            response.getOutputStream().close();
            
            verification.getExecutor().execute();
        }
        catch (JiBXException e) 
        {
            throw new ServletException(e);
        }
    }
}
