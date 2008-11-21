package org.curlybraces.synapse;

import static org.testng.Assert.assertEquals;

import java.net.URL;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateTest
{
    private final static String ID = "7b2fb945-c913-48aa-ad25-346e27c2064b";
    
    private final static Date DATE = new Date(1226453280480L);

    private final Server server = new Server();
    
    private Node node;

    @BeforeMethod public void startJetty() throws Exception
    {
        SelectChannelConnector connector = new SelectChannelConnector();
        // Set some timeout options to make debugging easier.
        connector.setMaxIdleTime(1000 * 60 * 60);
        connector.setSoLingerTime(-1);
        connector.setPort(8888);
        server.setConnectors(new Connector[] { connector });
        node = new Node();

        node.setURL(new URL("http", "localhost", 8888, "/synapse"));
        
        server.addHandler(new SynapseJettyHandler(node));
        server.start();
    }
    
    @Test public void update() throws Exception
    {
        Message missive = new Message();
        
        missive.setId(ID);
        missive.setDate(DATE);
        missive.setPersonId(ID);
        missive.setText("This is a test.");
        
        final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        
        node.addListener(new NodeListener()
        {
            @Override
            public void update(Message missive)
            {
                try
                {
                    queue.put("done");
                }
                catch (InterruptedException e)
                {
                }
            }
        });

        new Envelope(node.getURL(), new Synapse(new Update(missive))).send();

        String done = queue.take();
        assertEquals(done, "done");
    }
    
    @AfterMethod public void stopJetty() throws Exception
    {
        server.stop();
        server.join();
    }
}
