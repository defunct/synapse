package org.curlybraces.synapse;

// TODO Document.
public class RouteToken extends Command
{
    // TODO Document.
    private Term term;
    
    // TODO Document.
    public RouteToken()
    {
    }
    
    // TODO Document.
    public RouteToken(Stamp stamp, Term term)
    {
        super(stamp);
        this.term = term;
    }
    
    // TODO Document.
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
