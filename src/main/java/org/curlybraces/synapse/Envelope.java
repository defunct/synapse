package org.curlybraces.synapse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO Document.
public class Envelope
{
    // TODO Document.
    private final Logger logger = LoggerFactory.getLogger(Envelope.class);
    
    // TODO Document.
    private final URL url;
    
    // TODO Document.
    private final Synapse synapse;
    
    // TODO Document.
    public Envelope(URL url, Synapse synapse)
    {
        this.url = url;
        this.synapse = synapse;
    }
    
    // TODO Document.
    public Receipt send()
    {
        try
        {
            return trySend();
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        catch (JiBXException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    // TODO Document.
    private Receipt trySend() throws MalformedURLException, IOException, JiBXException
    {
        logger.debug("Sending synapse to {}.", url);
        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "text/xml;charset=utf-8");

        IBindingFactory synapses = BindingDirectory.getFactory(Synapse.class);
        IMarshallingContext m = synapses.createMarshallingContext();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        m.marshalDocument(synapse, "UTF-8", null, out);
        
        byte[] bytes = out.toByteArray();
        
        connection.setRequestProperty("Content-Length", Integer.toString(bytes.length));

        connection.getOutputStream().write(bytes);
        connection.getOutputStream().flush();
        
        Verification verification = null;
        connection.connect();
        if (connection.getResponseCode() == 200)
        {
            IBindingFactory verifications = BindingDirectory.getFactory(Verification.class);
            
            IUnmarshallingContext u = verifications.createUnmarshallingContext();

            InputStream in = connection.getInputStream();
            verification = (Verification) u.unmarshalDocument(in, "UTF-8");
        }
        
        logger.debug("Sent synapse to {} response {}", url, connection.getResponseCode());

        return new Receipt(connection.getResponseCode(), verification);
    }
}
