package org.curlybraces.synapse;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

/**
 * <p>
 * A routing table that maps a key to a {@link Route} instance, returning the
 * route to objects whose keys are greater than or equal to a specified key.
 * </p>
 * <h2>Router Splits</h2>
 * <p>
 * Each request to route include a list of alternates. After a router splits,
 * it keep the last split and inform any requests of the new split if not 
 * listed in the alternates.
 * </p>
 * <h2>Mirroring Routers</h2>
 * <p>
 * Splits are mirrored by keeping a version of each routing table, each child
 * table has a version number of the parent. The path is sent with each synapse.
 * Each part of the path contains the URL, UUID and version of the router. Each
 * router keeps a table of the path, with the newest version per id and a list
 * of the 16 or so most recent visitors who reported having that version.
 * </p>
 * <p>
 * When a synapse is routed and the path includes an older version, the synapse
 * is dropped, delayed, rerouted, not sure, but a new synapse is sent to tell
 * the URL that a newer version of the table is available at a different URL.
 * That routing table is fetched and copied.
 * </p>
 * 
 * @param <K>
 *            A Comparable implementation key type.
 */
public class Router<K extends Comparable<K>>
{
    /** The unique id of the router. */
    private final UUID id;

    /**
     * A map of routes stored in reverse order.
     */
    private final SortedMap<K, Route> mapOfRoutes;

    /**
     * Construct a router using the minimum value for the router key type to
     * find the specified route for least values for the specified UUID.
     * 
     * @param id
     *            The unique id of the router.
     */
    public Router(UUID id)
    {
        this.id = id;
        this.mapOfRoutes = new TreeMap<K, Route>(Collections.reverseOrder());
    }

    /**
     * Return the unique id of the router.
     * 
     * @return The unique id of the router.
     */
    public UUID getId()
    {
        return id;
    }

    /**
     * Return the route for values that are greater than or equal to the
     * specified key.
     * 
     * @param key
     *            The key to route.
     * @return The route to follow to find stored values greater than or equal
     *         to the specified key.
     */
    public Route get(K key)
    {
        SortedMap<K, Route> tail = mapOfRoutes.tailMap(key);
        return tail.get(tail.firstKey());
    }

    /**
     * Set the route for values that are greater than or equal to the specified
     * key.
     * 
     * @param key
     *            The key to the route.
     * @param route
     *            The route to follow to find stored values greater than or
     *            equal to the specified key.
     */
    public void add(K key, Route route)
    {
        mapOfRoutes.put(key, route);
    }
}
