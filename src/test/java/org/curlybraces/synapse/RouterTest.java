package org.curlybraces.synapse;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;


import java.util.UUID;

import org.testng.annotations.Test;

public class RouterTest
{
    private final static UUID ROUTE_ID = UUID.randomUUID();
    
    private final static UUID ROUTER_ID = UUID.randomUUID();

    @Test public void greatest()
    {
        Route route = new Route(ROUTE_ID, true);
        Router<String> router = new Router<String>(ROUTER_ID, "", route);
        assertEquals(ROUTE_ID, router.get("A").getId());
    }
    
    @Test public void search()
    {
        Route route = new Route(ROUTE_ID, true);
        Router<String> router = new Router<String>(ROUTER_ID, "", new Route(UUID.randomUUID(), true));
        router.add("G", route);
        assertEquals(ROUTE_ID, router.get("H").getId());
        assertEquals(ROUTE_ID, router.get("G").getId());
        assertFalse(ROUTE_ID.equals(router.get("F").getId()));
    }
}
