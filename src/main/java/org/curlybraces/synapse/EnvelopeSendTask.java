package org.curlybraces.synapse;

public class EnvelopeSendTask
extends Task
{
    private final Envelope envelope;
    
    public EnvelopeSendTask(Envelope envelope)
    {
        this.envelope = envelope;
    }
    
    @Override
    public void perform()
    {
        envelope.send();
    }
}
