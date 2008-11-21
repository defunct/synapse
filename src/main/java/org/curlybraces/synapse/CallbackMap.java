package org.curlybraces.synapse;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

public class CallbackMap<T>
{
    private final Map<UUID, T> mapOfCallbacks;
    
    private final LinkedList<UUID> calledback;

    public CallbackMap()
    {
        this.mapOfCallbacks = new HashMap<UUID, T>();
        this.calledback = new LinkedList<UUID>();
    }

    public void put(UUID callbackId, T callback)
    {
        if (mapOfCallbacks.containsKey(callbackId))
        {
            throw new IllegalArgumentException();
        }
        mapOfCallbacks.put(callbackId, callback);
    }
    
    public T callback(UUID callbackId)
    {
        T callback = mapOfCallbacks.remove(callbackId);
        if (callback != null)
        {
            if (calledback.size() == 256)
            {
                calledback.removeFirst();
            }
            calledback.addLast(callbackId);
        }
        return callback;
    }
}
