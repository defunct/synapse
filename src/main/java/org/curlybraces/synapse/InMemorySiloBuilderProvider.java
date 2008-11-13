package org.curlybraces.synapse;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class InMemorySiloBuilderProvider implements Provider<Silo.Builder>
{
    private final int capacity;
    
    @Inject
    public InMemorySiloBuilderProvider(@SiloCapacity int capacity)
    {
        this.capacity = capacity;
    }

    public Silo.Builder get()
    {
        return new InMemorySilo.Builder(capacity);
    }
}
