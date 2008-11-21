package org.curlybraces.synapse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class NatoAlphabet
{
    private final List<String> alphabet = new ArrayList<String>(26);
    
    public NatoAlphabet()
    {
        try
        {
            InputStream in = getClass().getResourceAsStream("nato.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                alphabet.add(line);
            }
        }
        catch (IOException e)
        {
        }
    }
    
    public String get(int index)
    {
        return alphabet.get(index);
    }
    
    public int size()
    {
        return alphabet.size();
    }
}
