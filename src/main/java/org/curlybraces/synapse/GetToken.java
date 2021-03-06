package org.curlybraces.synapse;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

// TODO Document.
public class GetToken extends Command
{
    // TODO Document.
    private final static int RETURN_COUNT = 3;
   
    // TODO Document.
    private UUID callbackId;
    
    // TODO Document.
    private URL from;
    
    // TODO Document.
    private Date atOrBefore;
    
    // TODO Document.
    private Term term;
    
    // TODO Document.
    public GetToken()
    {
    }

    // TODO Document.
    public GetToken(Stamp stamp, Term term, Date atOrBefore, URL from, UUID callbackId)
    {
        super(stamp);
        this.callbackId = callbackId;
        this.atOrBefore = atOrBefore;
        this.from = from;
        this.term = term;
    }
    
    // TODO Document.
    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        List<Token> listOfTokens = new ArrayList<Token>();
        Dictionary dictionary = node.getDictionary();
        Volume volume = dictionary.get(term);
        Entry entry = volume.get(term);
        Date at = atOrBefore;
        if (entry != null)
        {
            while (entry.atOrBefore(at, listOfTokens) && listOfTokens.size() < RETURN_COUNT)
            {
                Date oldest = listOfTokens.get(listOfTokens.size() - 1).getDate();
                at = new Date(oldest.getTime() - 1);
            }
        }
        synapse.shift(
                new GoTo(node.newStamp(), from),
                new ExecuteTokenCallback(node.newStamp(), callbackId, listOfTokens));
        queue.enqueue(synapse);
    }
}
