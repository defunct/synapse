package org.curlybraces.synapse;

import org.curlybraces.synapse.Archive.Builder;

import com.google.inject.Provider;

public class InMemoryArchiveBuilderProvider implements Provider<Archive.Builder>
{
    public Builder get()
    {
        return new InMemoryArchive.Builder();
    }
}
