package org.curlybraces.synapse;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Ultimately, I'd like to have the ability to listen at the socket, dropping
 * requests immediately, if the service is under attack. If the server gets
 * an attack, it can tell its router that it is moving to a different port. 
 */
public class SynapseServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private SynapseService service;
    
    @Override
    public void init(ServletConfig config) throws ServletException
    {
        Injector injector = Guice.createInjector(new ServletModule());
        Node node = injector.getInstance(Node.class);
        service = new SynapseService(node);
    }
    
    /**
     * This servlet handles only the POST method by delegating the 
     * handling to the single {@link Node} instance for this servlet. 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        service.post(request, response);
    }
}
