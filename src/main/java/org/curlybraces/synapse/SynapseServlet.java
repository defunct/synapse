package org.curlybraces.synapse;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Ultimately, I'd like to have the ability to listen at the socket, dropping
 * requests immediately, if the service is under attack. If the server gets
 * an attack, it can tell its router that it is moving to a different port. 
 */
public class SynapseServlet extends HttpServlet
{
    // TODO Document.
    private static final long serialVersionUID = 1L;

    // TODO Document.
    private SynapseService service;
    
    // TODO Document.
    @Override
    public void init(ServletConfig config) throws ServletException
    {
        Node node = new Node();
        service = new SynapseService(node);
    }
    
    /**
     * This servlet handles only the POST method by delegating the 
     * handling to the single {@link Node} instance for this servlet. 
     */
    // TODO Document.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        service.post(request, response);
    }
}
