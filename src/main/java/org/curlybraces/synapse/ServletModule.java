package org.curlybraces.synapse;

import com.google.inject.Binder;
import com.google.inject.Module;

public class ServletModule implements Module
{
    public void configure(Binder binder)
    {
        binder.bind(int.class)
              .annotatedWith(SiloCapacity.class)
              .toInstance(3);
        binder.bind(Silo.Builder.class)
              .toProvider(InMemorySiloBuilderProvider.class);
        binder.bind(Archive.Builder.class)
              .toProvider(InMemoryArchiveBuilderProvider.class);
    }
}
