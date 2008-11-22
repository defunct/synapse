package org.curlybraces.synapse;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

import org.testng.annotations.Test;

public class SetProfileTest
{
    private final static String ID = "7b2fb945-c913-48aa-ad25-346e27c2064b";

    private Contributor newContributor() throws MalformedURLException
    {
        Node node = new Node();
        node.setURL(new URL("http://localhost:8888/synapse"));
        return node;
    }

    @Test
    public void setProfile() throws InterruptedException, MalformedURLException
    {
        Profile profile = new Profile(UUID.fromString(ID));
        profile.setName("Alan Gutierrez");

        final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();

        Contributor contributor = newContributor();
        
        contributor.addListener(new NodeListener()
        {
            @Override
            public void setProfile(Profile profile)
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
        
        contributor.setProfile(profile);
        
        String done = queue.take();
        assertEquals(done, "done");
    }
}
