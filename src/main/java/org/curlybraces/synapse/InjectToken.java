package org.curlybraces.synapse;

public class InjectToken extends Command
{
    private Token token;
    
    public InjectToken()
    {
    }

    public InjectToken(Stamp stamp, Token token)
    {
        super(stamp);
        this.token = token;
    }
    
    @Override
    public void execute(Node node, SynapseQueue queue, Synapse synapse)
    {
        Term term = token.getTerm();
        Dictionary dictionary = node.getDictionary();
        Volume volume = dictionary.get(term);
        Entry entry = volume.get(term);
        if (entry == null)
        {
            entry = new Entry(term);
            volume.put(term, entry);
        }
        entry.add(token.getDate(), token.getMessageId());
        queue.enqueue(synapse);
    }
}
