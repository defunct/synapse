package org.curlybraces.synapse;

// TODO Document.
public class EnvelopeExecutor implements Executor
{
    // TODO Document.
    private final Envelope envelope;

    // TODO Document.
    public EnvelopeExecutor(Envelope envelope)
    {
        this.envelope = envelope;
    }

    // TODO Document.
    public void execute()
    {
        envelope.send();
    }
}
