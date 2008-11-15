package org.curlybraces.synapse;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class DiscoverTest
{
    private Server[] servers = new Server[3];
    
    @BeforeMethod public void startJetty() throws Exception
    {
        for (int i = 0; i < servers.length; i++)
        {
            SocketConnector connector = new SocketConnector();
            // Set some timeout options to make debugging easier.
            connector.setMaxIdleTime(1000 * 60 * 60);
            connector.setSoLingerTime(-1);
            connector.setPort(8888 + i);
            
            servers[i] = new Server();
            servers[i].setConnectors(new Connector[] { connector });
    
            Injector injector = Guice.createInjector(new ServletModule());
            Node node = injector.getInstance(Node.class);
            
            servers[i].addHandler(new SynapseJettyHandler(node));
            servers[i].start();
        }
    }
    
    @Test public void discover()
    {
        
    }
    
    @AfterMethod public void stopJetty() throws Exception
    {
        for (int i = 0; i < servers.length; i++)
        {
            servers[i].stop();
            servers[i].join();
        }
    }
}
