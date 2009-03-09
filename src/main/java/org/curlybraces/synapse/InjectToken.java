package org.curlybraces.synapse;

// TODO Document.
public class InjectToken extends Command
{
    // TODO Document.
    private Token token;
    
    // TODO Document.
    public InjectToken()
    {
    }

    // TODO Document.
    public InjectToken(Stamp stamp, Token token)
    {
        super(stamp);
        this.token = token;
    }
    
    // TODO Document.
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
