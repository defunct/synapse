package org.curlybraces.synapse;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

public class Locator
{
    private final String host;
    
    private final int port;
    
    public Locator(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    public Result sendCommand(Synapse synapse) throws IOException, JiBXException
    {
        URL url = new URL("http", host, port, "/synapse");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/xml;charset=utf-8");

        IBindingFactory synapses = BindingDirectory.getFactory(Synapse.class);
        IMarshallingContext m = synapses.createMarshallingContext();

        m.marshalDocument(synapse, "UTF-8", null, connection.getOutputStream());

        Verification verification = null;
        InputStream in = connection.getInputStream();
        if (connection.getResponseCode() == 200)
        {
            IBindingFactory verifications = BindingDirectory.getFactory(Verification.class);
            
            IUnmarshallingContext u = verifications.createUnmarshallingContext();
            verification = (Verification) u.unmarshalDocument(in, "UTF-8");
        }

        return new Result(connection.getResponseCode(), verification);
    }
    
    @Override
    public boolean equals(Object object)
    {
        if (object instanceof Locator)
        {
            Locator locator = (Locator) object;
            return host.equals(locator.host)
                && port == locator.port;
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 1;
        hash = hash * 37 + host.hashCode();
        hash = hash * 37 + port;
        return hash;
    }
}
