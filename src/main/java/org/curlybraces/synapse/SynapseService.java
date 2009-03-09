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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO Document.
public class SynapseService
{
    // TODO Document.
    private final Logger logger = LoggerFactory.getLogger(SynapseService.class);

    // TODO Document.
    private final Node node;

    // TODO Document.
    public SynapseService(Node node)
    {
        this.node = node;
    }
    
    // TODO Document.
    public Node getNode()
    {
        return node;
    }
    
    // TODO Document.
    public void post(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        try
        {
            logger.debug("Process {} request to {}.", request.getMethod(), request.getRequestURI());
            
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
