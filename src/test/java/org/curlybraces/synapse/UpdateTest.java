package org.curlybraces.synapse;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class UpdateTest
{
    private final static String ID = "7b2fb945-c913-48aa-ad25-346e27c2064b";
    
    private final static Date DATE = new Date(1226453280480L);

    private final Server server = new Server();
    
    private Node node;

    @BeforeMethod public void startJetty() throws Exception
    {
        SocketConnector connector = new SocketConnector();
        // Set some timeout options to make debugging easier.
        connector.setMaxIdleTime(1000 * 60 * 60);
        connector.setSoLingerTime(-1);
        connector.setPort(8888);
        server.setConnectors(new Connector[] { connector });
        Injector injector = Guice.createInjector(new ServletModule());
        node = injector.getInstance(Node.class);

        node.setLocator(new Locator("localhost", 8888));
        
        server.addHandler(new SynapseJettyHandler(node));
        server.start();
    }
    
    @Test public void update() throws Exception
    {
//        Locator locator = new Locator("localhost", 8888);
        
        Missive missive = new Missive();
        
        missive.setId(ID);
        missive.setDate(DATE);
        missive.setPersonId(ID);
        missive.setMessage("This is a test.");
        
        
        final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        
        node.addListener(new SynapseListener()
        {
            @Override
            public void update(Missive missive)
            {
                queue.add("");
            }
        });

    //    locator.sendCommand(new Synapse(new Update(missive)));
        
        Thread.sleep(5000);
    }
    
    @AfterMethod public void stopJetty() throws Exception
    {
        server.stop();
        server.join();
    }
}
