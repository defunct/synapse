package org.curlybraces.synapse;

/**
 * Manages a mirror of a node. This mirrors messages and profiles in the node
 * and the node archive of term entries. A mirror is created for a node,
 * two for each node. It is a log. Tail changes. One line for each change.
 * Create, add, (remove?) and split.
 * <p>
 * Mirror checks and when down, it becomes the node. Its mirrors mirror.
 * Other mirror waits for the merge. Once merge is complete, other mirrors
 * collapse. Then load balancing takes place.
 * <p>
 * Mirrors can compact themselves at their own discretion. If an archive
 * is off-loading and splitting, a lot, the mirror can build the node,
 * save the node, and tail the changes after that. 
 * 
 * @author Alan Gutierrez
 */
public class Mirror
{
}
