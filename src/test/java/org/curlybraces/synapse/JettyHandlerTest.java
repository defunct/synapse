package org.curlybraces.synapse;

import java.net.URI;
import java.net.URL;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class JettyHandlerTest
{
    private Server server = new Server();

    @BeforeMethod public void startJetty() throws Exception
    {
        SocketConnector connector = new SocketConnector();
        // Set some timeout options to make debugging easier.
        connector.setMaxIdleTime(1000 * 60 * 60);
        connector.setSoLingerTime(-1);
        connector.setPort(8888);
        server.setConnectors(new Connector[] { connector });

        Injector injector = Guice.createInjector(new ServletModule());
        Node node = injector.getInstance(Node.class);
        
        server.addHandler(new SynapseJettyHandler(node));
        server.start();
    }
    
    
    @Test public void test() throws Exception 
    {
        URL url = URI.create("http://localhost:888/synapse").toURL();
        Synapse synapse = new Synapse(new Echo("Hello, World!"));
        
        new Envelope(url, synapse);
        
        Thread.sleep(2000);
    }

    @AfterMethod public void stopJetty() throws Exception
    {
        server.stop();
        server.join();
    }
}
