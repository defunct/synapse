package org.curlybraces.synapse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Entry
{
    private int size;
    
    private final Term term;
    
    private final long[] dates;
    
    private final long[] messageIds;
    
    public Entry(Term term)
    {
        this.term = term;
        this.dates = new long[1024];
        this.messageIds = new long[1024 * 2];
    }

    private int search(Date date)
    {
        long time = date.getTime();
        int low = 0;
        int high = size - 1;
        while (low <= high)
        {
            int mid = (low + high) >>> 1;
            long v = dates[mid];
            if (v < time)
            {
                low = mid + 1;
            }
            else if (time < v)
            {
                high = mid - 1;
            }
            else
            {
                int prev = mid;
                do
                {
                    mid = prev;
                    prev = mid - 1;
                }
                while (prev > 0 && dates[prev] == time);
                return mid;
            }
        }
        return -(low + 1);
    }
    
    public int seek(int index, Date date, UUID messageId)
    {
        long time = date.getTime();
        while (index < size && dates[index] == time)
        {
            if (messageIds[index * 2] == messageId.getMostSignificantBits()
             && messageIds[index * 2 + 1] == messageId.getLeastSignificantBits())
            {
                return index;
            }
            index++;
        }
        return -1;
    }
    
    private void insert(int index, Date date, UUID messageId)
    {
        System.arraycopy(dates, index, dates, index + 1, size - index);
        System.arraycopy(messageIds, index * 2, messageIds, index * 2 + 2, (size - index) * 2);
        dates[index] = date.getTime();
        messageIds[index * 2] = messageId.getMostSignificantBits();
        messageIds[index * 2 + 1] = messageId.getLeastSignificantBits();
        size++;
    }
    
    public void add(Date date, UUID messageId)
    {
        int index = search(date);
        if (index < 0)
        {
            insert(-(index + 1), date, messageId);
        }
        else if (seek(index, date, messageId) == -1)
        {
            insert(index, date, messageId);
        }
    }
    
    public List<Token> get(Date date)
    {
        int index = search(date);
        if (index < 0)
        {
            return Collections.emptyList();
        }
        long time = date.getTime();
        List<Token> tokens = new ArrayList<Token>();
        for (int i = index; i < size && dates[i] == time; i++)
        {
            Token token = new Token();
            token.setTerm(term);
            token.setDate(date);
            token.setMessageId(new UUID(messageIds[i * 2], messageIds[i * 2 + 1]));
            tokens.add(token);
        }
        return tokens;
    }
    
    public boolean atOrBefore(Date date, List<Token> listOfTokens)
    {
        int index = search(date);
        if (index == 0)
        {
            return false;
        }
        if (index < 0)
        {
            index = -(index + 1);
        }
        if (index == size || dates[index] != date.getTime())
        {
            index--;
        }
        long time = dates[index];
        for (int i = index; i > 0 && dates[i] == time; i--)
        {
            Token token = new Token();
            token.setTerm(term);
            token.setDate(new Date(dates[i]));
            token.setMessageId(new UUID(messageIds[i * 2], messageIds[i * 2 + 1]));
            listOfTokens.add(token);
        }
        return true;
    }
}
