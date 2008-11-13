package org.curlybraces.synapse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.handler.AbstractHandler;

public class SynapseJettyHandler extends AbstractHandler
{
    private final SynapseService service;
    
    public SynapseJettyHandler(Node node)
    {
        this.service = new SynapseService(node);
    }
    
    public void handle(String target, HttpServletRequest request,
            HttpServletResponse response, int dispatch)
        throws IOException, ServletException
    {
        service.post(request, response);
    }
}
