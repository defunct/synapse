package org.curlybraces.synapse;

/**
 * A task that has been queued in a <code>BlockingQueue</code>.
 * <p>
 * This class includes a {@link #isTerminal()} method, so that the provider
 * thread can send a terminal task to the consumer thread, using the queue
 * itself to send the message to terminate.
 * 
 * @author Alan Gutierrez
 */
public class Task
{
    /**
     * If true, this is the last task in the queue and the listening thread
     * should terminate.
     * 
     * @return True if the listening thread should terminate.
     */
    public boolean isTerminal()
    {
        return false;
    }

    /**
     * Perform the queued task.
     */
    public void perform()
    {
    }
}
