package org.curlybraces.synapse;

// TODO Document.
public class UpdateCallback implements Runnable
{
    // TODO Document.
    private final UpdateListener listener;

    // TODO Document.
    public UpdateCallback(UpdateListener listener)
    {
        this.listener = listener;
    }

    // TODO Document.
    public void run()
    {
        listener.updated();
    }
}
