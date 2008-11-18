package org.curlybraces.synapse;


public class UpdateCallback implements Runnable
{
    private final UpdateListener listener;

    public UpdateCallback(UpdateListener listener)
    {
        this.listener = listener;
    }

    public void run()
    {
        listener.updated();
    }
}
