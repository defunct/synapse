package org.curlybraces.synapse;


public class RouteTokenSynapse extends Command
{
    private String term;
    
    public RouteTokenSynapse()
    {
    }
    
    public RouteTokenSynapse(String term)
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
            synapse.shift(new RouteTokenSynapse(term));
        }
        node.sendCommand(route.get(synapse), synapse);
    }
}
