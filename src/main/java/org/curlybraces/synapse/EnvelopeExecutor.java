package org.curlybraces.synapse;

public class EnvelopeExecutor implements Executor
{
    private final Envelope envelope;

    public EnvelopeExecutor(Envelope envelope)
    {
        this.envelope = envelope;
    }

    public void execute()
    {
        envelope.send();
    }
}
