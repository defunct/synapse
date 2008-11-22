package org.curlybraces.synapse;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

import org.testng.annotations.Test;

public class UpdateTest
{
    private final static String ID = "7b2fb945-c913-48aa-ad25-346e27c2064b";
    
    private final static Date DATE = new Date(1226453280480L);

    private final static String PERSON_ID = "4d7101d0-ccdf-4599-8132-4fb81158ace3";

    private Contributor newContributor() throws MalformedURLException
    {
        Node node = new Node();
        node.setURL(new URL("http://localhost:8888/synapse"));
        return node;
    }
    
    @Test public void update() throws Exception
    {
        Contributor contributor = newContributor();
        
        Message message = new Message();
        
        message.setId(ID);
        message.setDate(DATE);
        message.setProfileId(PERSON_ID);
        message.setText("This is a test.");
        
        final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        
        contributor.addListener(new NodeListener()
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

        contributor.update(message);

        String done = queue.take();
        assertEquals(done, "done");
    }
}
