package org.curlybraces.synapse;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.testng.annotations.Test;

public class SearchTest
{
    private final static Date DATE = new Date(1226453280480L);

    private final static UUID ID = UUID.fromString("7b2fb945-c913-48aa-ad25-346e27c2064b");

    private final static UUID PROFILE_ID = UUID.fromString("4d7101d0-ccdf-4599-8132-4fb81158ace3");

    private UUID getId(int inc)
    {
        return new UUID(ID.getMostSignificantBits(), ID.getLeastSignificantBits() + inc);
    }

    private Date getDate(int inc)
    {
        return new Date(DATE.getTime() + inc * 60000);
    }
    
    public Contributor newContributor() throws MalformedURLException
    {
        Node node = new Node();
        node.setURL(new URL("http://localhost:8888/snapse"));
        return node;
    }

    @Test
    public void search() throws MalformedURLException
    {
        Contributor contributor = newContributor();
                    
        NatoAlphabet alphabet = new NatoAlphabet();

        Random random = new Random(1L);

        int date = 0;
        int id = 0;

        int matches = 3;
        while (matches-- != 0)
        {
            int count = 24;
            while (count != 0)
            {
                SortedSet<String> seen = new TreeSet<String>();

                StringBuilder newString = new StringBuilder();
                String separator = "";

                while (seen.size() != 3)
                {
                    String string = alphabet.get(random.nextInt(alphabet.size()));

                    newString.append(separator);
                    newString.append(string);
                    separator = " ";

                    seen.add(string);
                }

                int abc = 0;
                for (int i = 0; i < 3; i++)
                {
                    if (seen.contains(alphabet.get(i)))
                    {
                        abc++;
                    }
                }

                if (abc != 3)
                {
                    Message message = new Message();

                    message.setId(getId(id++));
                    message.setProfileId(PROFILE_ID);
                    message.setDate(getDate(date++));
                    message.setText(newString.toString());

                    contributor.update(message);

                    count--;
                }
            }

            Message message = new Message();

            message.setId(getId(id++));
            message.setProfileId(PROFILE_ID);
            message.setDate(getDate(date++));
            message.setText("alpha beta charlie");

            contributor.update(message);
        }

        MatchAll all = new MatchAll();

        MatchAny any = new MatchAny();
        any.add(new Term("alpha", Term.KEYWORD));
        all.add(any);

        any = new MatchAny();
        any.add(new Term("beta", Term.KEYWORD));
        all.add(any);

        any = new MatchAny();
        any.add(new Term("charlie", Term.KEYWORD));
        all.add(any);
        
        final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

        contributor.search(all, new NodeListener()
        {
            public void found(MatchAll search, Message message, Profile profile)
            {
                try
                {
                    queue.put(message.getText());
                }
                catch (InterruptedException e)
                {
                }
            }
        });
        
        for (int i = 0; i < 3; i++)
        {
            try
            {
                assertEquals("alpha beta charlie", queue.take());
            }
            catch (InterruptedException e)
            {
            }
        }
    }
}
