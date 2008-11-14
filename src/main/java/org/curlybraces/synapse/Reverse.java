package org.curlybraces.synapse;

import java.util.Comparator;

public class Reverse<T extends Comparable<T>>
implements Comparator<T>
{
    public int compare(T o1, T o2)
    {
        return o2.compareTo(o1);
    }
}
