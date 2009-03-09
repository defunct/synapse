package org.curlybraces.synapse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

// TODO Document.
public class MergeAll
{
    // TODO Document.
    private final MatchAll search;
    
    // TODO Document.
    private final Node node;
    
    // TODO Document.
    private final Set<Set<Term>> setOfSetsOfTerms;
    
    // TODO Document.
    private final Map<Set<Term>, SortedMap<MergeKey, Token>> mapOfMapsOfTokens;
    
    // TODO Document.
    private final NodeListener listener;
    
    // TODO Document.
    private final int results;
    
    // TODO Document.
    private int found;
    
    // TODO Document.
    private MergeKey oldest;

    // TODO Document.
    private int count;
    
    // TODO Document.
    public MergeAll(Node node, MatchAll search, int results, NodeListener listener)
    {
        Map<Set<Term>, SortedMap<MergeKey, Token>> mapOfMapsOfTokens;
        mapOfMapsOfTokens = new HashMap<Set<Term>, SortedMap<MergeKey,Token>>();
        
        Set<Set<Term>> setOfSetsOfTerms = search.getTerms();
        for (Set<Term> setOfTerms : setOfSetsOfTerms)
        {
            SortedMap<MergeKey, Token> mapOfTokens = new TreeMap<MergeKey, Token>(Collections.reverseOrder());
            mapOfMapsOfTokens.put(setOfTerms, mapOfTokens);
        }
        
        this.search = search;
        
        this.node = node;
        this.results = results;
        this.listener = listener;
        this.setOfSetsOfTerms = setOfSetsOfTerms;
        this.mapOfMapsOfTokens = mapOfMapsOfTokens;
        
        this.oldest = new MergeKey(new Date(Long.MAX_VALUE));
    }
    
    // TODO Document.
    public void next()
    {
        List<Set<Term>> listOfSetsOfTerms = new ArrayList<Set<Term>>();
        for (Set<Term> setOfTerms : setOfSetsOfTerms)
        {
            SortedMap<MergeKey, Token> mapOfTokens = mapOfMapsOfTokens.get(setOfTerms).tailMap(oldest);
            if (mapOfTokens.size() == 0 || oldest.compareTo(mapOfTokens.firstKey()) < 1)
            {
                listOfSetsOfTerms.add(setOfTerms);
            }
        }
        
        count = listOfSetsOfTerms.size();
        for (Set<Term> setOfTerms : listOfSetsOfTerms)
        {
            MergeAny any = new MergeAny(this, setOfTerms);
            UUID callbackId = node.newUUID();
            node.getTokenCallbacks().put(callbackId, any);
            
            for (Term term : setOfTerms)
            {
                Synapse search = new Synapse(
                        new RouteToken(node.newStamp(), term),
                        new GetToken(node.newStamp(), term, oldest.getDate(), node.getURL(), callbackId));
                new NodeExecutor(node, search).execute();
            }
        }
    }
    
    // TODO Document.
    public void merge(Set<Term> setOfTerms, List<Token> listOfTokens)
    {
        if (listOfTokens.size() == 0)
        {
            throw new IllegalStateException();
        }
        for (Token token : listOfTokens)
        {
            mapOfMapsOfTokens.get(setOfTerms).put(new MergeKey(token), token);
        }
        if (--count == 0)
        {
            LinkedList<SortedMap<MergeKey, Token>> listOfMatching = new LinkedList<SortedMap<MergeKey,Token>>();
            MATCHING: for(;;)
            {
                System.out.println("Matching.");
                for (Map.Entry<Set<Term>, SortedMap<MergeKey, Token>> entry : mapOfMapsOfTokens.entrySet())
                {
                    Iterator<Map.Entry<MergeKey, Token>> entries = entry.getValue().entrySet().iterator();
                    while (entries.hasNext())
                    {
                        Map.Entry<MergeKey, Token> instance = entries.next();
                        if (instance.getKey().compareTo(oldest) > 0)
                        {
                            entries.remove();
                        }
                        else
                        {
                            break;
                        }
                    }
                    if (entry.getValue().size() == 0)
                    {
                        break MATCHING;
                    }
                    if (entry.getValue().firstKey().compareTo(oldest) < 0)
                    {
                        oldest = entry.getValue().firstKey();
                    }
                    if (listOfMatching.size() != 0)
                    {
                        MergeKey previous = listOfMatching.getLast().firstKey();
                        MergeKey current = entry.getValue().firstKey();
                        int compare = previous.compareTo(current);
                        if (compare == 0)
                        {
                            listOfMatching.addLast(entry.getValue());
                            if (listOfMatching.size() == mapOfMapsOfTokens.size())
                            {
                                UUID callbackId = node.newUUID();
                                node.getMessageCallbacks().put(callbackId,new SearchListener(node, search, listener));
                                Synapse getMessage = new Synapse(
                                        new RouteMessage(node.newStamp(), current.getMessageId()),
                                        new GetMessage(node.newStamp(), current.getMessageId(), node.getURL(), callbackId)
                                        );
                                new NodeExecutor(node, getMessage).execute();
                                if (++found == results)
                                {
                                    return;
                                }
                                for (SortedMap<MergeKey, Token> matched : listOfMatching)
                                {
                                    matched.remove(current);
                                }
                                listOfMatching.clear();
                            }
                        }
                        else if (compare > 0)
                        {
                            listOfMatching.clear();
                            oldest = current;
                            continue MATCHING;
                        }
                    }
                    else
                    {
                        listOfMatching.addLast(entry.getValue());
                    }
                }
            }
            next();
        }
    }
}
