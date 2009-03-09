package org.curlybraces.synapse;

// TODO Document.
public class EnvelopeSendTask
extends Task
{
    // TODO Document.
    private final Envelope envelope;
    
    // TODO Document.
    public EnvelopeSendTask(Envelope envelope)
    {
        this.envelope = envelope;
    }
    
    // TODO Document.
    @Override
    public void perform()
    {
        envelope.send();
    }
}
