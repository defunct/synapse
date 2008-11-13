package org.curlybraces.synapse;

import java.util.Date;
import java.util.UUID;

public class QueryTerm extends Command
{
    private short type;
    
    private String word;
    
    private Date atOrBefore;
 
    public QueryTerm()
    {
    }

    public QueryTerm(short type, String word, Date atOrBefore)
    {
        super(UUID.randomUUID(), new Date());

        this.type = type;
        this.word = word;
        this.atOrBefore = atOrBefore;
    }
  
    public void execute(Node node)
    {
        node.getSiloManager().fetch(type, word, atOrBefore);
    }
}
