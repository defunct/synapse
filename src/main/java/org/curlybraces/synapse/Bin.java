package org.curlybraces.synapse;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Storage for objects grouped by a range of key values. Each bin stores an
 * object for a range of UUIDs. A bin is essentially a leaf node in b-tree. When
 * a bin reaches a capacity, it splits.
 * <p>
 * Upon splitting, the storage object that contains both bins can still serve as
 * the terminus for a search for the bin.
 * 
 * @author Alan Gutierrez
 * 
 * @param <T>
 *            The type of stored object.
 */
public class Bin<T>
{
    /** Map of objects by id. */
    private final Map<UUID, T> mapOfObjects;

    /** All keys are less than this end value. */
    private final UUID end;

    /**
     * Create a bin that will store objects keyed by keys less than the
     * specified end value.
     * 
     * @param end
     *            All keys in this bin are less than this end value.
     */
    public Bin(UUID end)
    {
        this.mapOfObjects = new TreeMap<UUID, T>();
        this.end = end;
    }

    /**
     * Return the end value, the successor value to the maximum key value that
     * can be stored in this bin.
     * 
     * @return The end value.
     */
    public UUID getEnd()
    {
        return end;
    }

    /**
     * Put an object into the bin keyed on the specified key.
     * 
     * @param id
     *            The key.
     * @param object
     *            The object.
     */
    public void put(UUID id, T object)
    {
        mapOfObjects.put(id, object);
    }

    /**
     * Get the object mapped to the specified key.
     * 
     * @param id
     *            The key.
     * @return The object mapped to the specified key or <code>null</code> if
     *         the key does not exist in the bin.
     */
    public T get(UUID id)
    {
        return mapOfObjects.get(id);
    }
}
