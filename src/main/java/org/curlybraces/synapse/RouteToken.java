package org.curlybraces.synapse;


public class RouteToken extends Command
{
    private Term term;
    
    public RouteToken()
    {
    }
    
    public RouteToken(Stamp stamp, Term term)
    {
        super(stamp);
        this.term = term;
    }
    
    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        Network<Term> tokenNetwork = node.getTokenNetwork();
        Router<Term> router = tokenNetwork.get(tokenNetwork.getRootId());
        Route route = router.get(term);
        if (!route.isLeaf())
        {
            synapse.shift(new RouteToken(node.newStamp(), term));
        }
        queue.enqueue(route.get(synapse), synapse);
    }
}
