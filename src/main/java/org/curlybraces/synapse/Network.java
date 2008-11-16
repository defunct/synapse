package org.curlybraces.synapse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Network<K extends Comparable<K>>
{
    private final UUID rootId;

    private final Map<UUID, Router<K>> mapOfRouters;
    
    public Network(UUID rootId, K min)
    {
        Router<K> root = new Router<K>(rootId);

        this.rootId = rootId;
        this.mapOfRouters = new HashMap<UUID, Router<K>>();
        this.mapOfRouters.put(rootId, root);
    }
    
    public UUID getRootId()
    {
        return rootId;
    }
    
    public Router<K> get(UUID id)
    {
        return mapOfRouters.get(id);
    }
}
