package org.curlybraces.synapse;


public class RouteToken extends Command
{
    private String term;
    
    public RouteToken()
    {
    }
    
    public RouteToken(String term)
    {
        this.term = term;
    }
    
    @Override
    public void execute(Node node, Synapse synapse)
    {
        Network<String> tokenNetwork = node.getTokenNetwork();
        Router<String> router = tokenNetwork.get(tokenNetwork.getRootId());
        Route route = router.get(term);
        if (!route.isLeaf())
        {
            synapse.shift(new RouteToken(term));
        }
        node.sendCommand(route.get(synapse), synapse);
    }
}
