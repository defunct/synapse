package org.curlybraces.synapse;

import java.util.Date;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.servlet.ServletHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ServletTest
{
    private final static String ID = "7b2fb945-c913-48aa-ad25-346e27c2064b";
    
    private final static Date DATE = new Date(1226453280480L);

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
        Locator locator = new Locator("localhost", 8888);
        Synapse synapse = new Synapse(new Echo("Hello, World!"));
        locator.sendCommand(synapse);
    }
    
    public void update() throws Exception
    {
        Locator locator = new Locator("localhost", 8888);
        
        Missive missive = new Missive();
        
        missive.setId(ID);
        missive.setDate(DATE);
        missive.setPersonId(ID);
        missive.setMessage("This is a test.");
        
        locator.sendCommand(new Synapse(new Update(missive)));
    }
    
    @AfterMethod public void stopJetty() throws Exception
    {
        server.stop();
        server.join();
    }
}
