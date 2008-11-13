package org.curlybraces.synapse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ArchiveManager
{
    private final Map<UUID, Archive> mapOfArchives = new HashMap<UUID, Archive>();
    
    private final Provider<Archive.Builder> newArchiveProvider;
    
    @Inject
    public ArchiveManager(Provider<Archive.Builder> newArchiveProvider)
    {
        this.newArchiveProvider = newArchiveProvider;
    }
    
    public Archive get(UUID personId)
    {
        Archive archive = mapOfArchives.get(personId);
        if (archive == null)
        {
            Archive.Builder newArchive = newArchiveProvider.get();
            newArchive.setPersonId(personId);
            archive = newArchive.newArchive();
            mapOfArchives.put(personId, archive);
        }
        return archive;
    }
}
