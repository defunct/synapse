package org.curlybraces.synapse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// TODO Document.
public class Network<K extends Comparable<K>>
{
    // TODO Document.
    private final UUID rootId;

    // TODO Document.
    private final Map<UUID, Router<K>> mapOfRouters;
    
    // TODO Document.
    public Network(UUID rootId, K min)
    {
        Router<K> root = new Router<K>(rootId);

        this.rootId = rootId;
        this.mapOfRouters = new HashMap<UUID, Router<K>>();
        this.mapOfRouters.put(rootId, root);
    }
    
    // TODO Document.
    public UUID getRootId()
    {
        return rootId;
    }
    
    // TODO Document.
    public Router<K> get(UUID id)
    {
        return mapOfRouters.get(id);
    }
}
