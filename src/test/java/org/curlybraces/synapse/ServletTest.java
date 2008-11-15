package org.curlybraces.synapse;

import java.net.URL;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.servlet.ServletHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ServletTest
{
    Server server = new Server();

    @BeforeMethod public void startJetty() throws Exception
    {
        SocketConnector connector = new SocketConnector();
        // Set some timeout options to make debugging easier.
        connector.setMaxIdleTime(1000 * 60 * 60);
        connector.setSoLingerTime(-1);
        connector.setPort(8888);
        server.setConnectors(new Connector[] { connector });


        ServletHandler handler = new ServletHandler();
       
        handler.addServletWithMapping(SynapseServlet.class, "/synapse");
        server.addHandler(handler);
        server.start();
    }
    
    @Test public void test() throws Exception 
    {
        URL url = new URL("http", "localhost", 8888, "/synapse");
        Synapse synapse = new Synapse(new Echo("Hello, World!"));

        new Envelope(url, synapse).send();
        
        Thread.sleep(2000);
    }
    
    @AfterMethod public void stopJetty() throws Exception
    {
        server.stop();
        server.join();
    }
}
