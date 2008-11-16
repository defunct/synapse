package org.curlybraces.synapse;

import java.util.Date;
import java.util.UUID;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class Dictionary
{
    private final Provider<Silo.Builder> newSiloProvider;
    
    @Inject
    public Dictionary(Provider<Silo.Builder> newSiloProvider)
    {
        this.newSiloProvider = newSiloProvider;
    }
    
    public void fetch(short type, String word, Date atOrBefore)
    {
    }
    
    public void create(Synapse synapse, UUID previousId)
    {
        Silo.Builder newSilo = newSiloProvider.get();
        newSilo.setPreviousId(previousId);
    }
}
