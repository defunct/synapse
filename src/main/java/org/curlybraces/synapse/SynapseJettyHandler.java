package org.curlybraces.synapse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.handler.AbstractHandler;

// TODO Document.
public class SynapseJettyHandler extends AbstractHandler
{
    // TODO Document.
    private final SynapseService service;
    
    // TODO Document.
    public SynapseJettyHandler(Node node)
    {
        this.service = new SynapseService(node);
    }
    
    // TODO Document.
    public void handle(String target, HttpServletRequest request,
            HttpServletResponse response, int dispatch)
        throws IOException, ServletException
    {
        service.post(request, response);
    }
}
