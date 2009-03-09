package org.curlybraces.synapse;

import java.util.Date;
import java.util.UUID;

// TODO Document.
public class MergeKey implements Comparable<MergeKey>
{
    // TODO Document.
    private final Date date;

    // TODO Document.
    private final UUID messageId;

    // TODO Document.
    public MergeKey(Date date)
    {
        this.date = date;
        this.messageId = new UUID(Long.MIN_VALUE, Long.MIN_VALUE);
    }

    // TODO Document.
    public MergeKey(Token token)
    {
        this.date = token.getDate();
        this.messageId = token.getMessageId();
    }

    // TODO Document.
    public Date getDate()
    {
        return date;
    }

    // TODO Document.
    public UUID getMessageId()
    {
        return messageId;
    }

    // TODO Document.
    public int compareTo(MergeKey mergeKey)
    {
        int compare = date.compareTo(mergeKey.getDate());
        if (compare == 0)
        {
            compare = messageId.compareTo(mergeKey.getMessageId());
        }
        return compare;
    }
    
    // TODO Document.
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
    
    // TODO Document.
    @Override
    public int hashCode()
    {
        int hash = 1;
        hash = hash * 37 + date.hashCode();
        hash = hash * 37 + messageId.hashCode();
        return hash;
    }
    
    // TODO Document.
    public String toString()
    {
        return "[" + date.toString() + ", " + messageId.toString() + "]";
    }
}
