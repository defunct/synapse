package org.curlybraces.synapse;

import java.util.Date;
import java.util.UUID;

public class MergeKey implements Comparable<MergeKey>
{
    private final Date date;

    private final UUID messageId;

    public MergeKey(Date date)
    {
        this.date = date;
        this.messageId = new UUID(Long.MIN_VALUE, Long.MIN_VALUE);
    }

    public MergeKey(Token token)
    {
        this.date = token.getDate();
        this.messageId = token.getMessageId();
    }

    public Date getDate()
    {
        return date;
    }

    public UUID getMessageId()
    {
        return messageId;
    }

    public int compareTo(MergeKey mergeKey)
    {
        int compare = date.compareTo(mergeKey.getDate());
        if (compare == 0)
        {
            compare = messageId.compareTo(mergeKey.getMessageId());
        }
        return compare;
    }
    
    @Override
    public boolean equals(Object object)
    {
        if (object instanceof MergeKey)
        {
            MergeKey mergeKey = (MergeKey) object;
            return date.equals(mergeKey.getDate())
                && messageId.equals(mergeKey.getMessageId());
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 1;
        hash = hash * 37 + date.hashCode();
        hash = hash * 37 + messageId.hashCode();
        return hash;
    }
    
    public String toString()
    {
        return "[" + date.toString() + ", " + messageId.toString() + "]";
    }
}
