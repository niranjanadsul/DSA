package LinkedList.DoublyLinkedList;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheUsingLinkedHashMap_2 extends LinkedHashMap<Integer, Integer> {
    //Why LinkedHashMap
    /*LinkedHashMap maintains insertion order by default, b
    ut if we construct it with accessOrder=true, it maintains access order.

    That means whenever we call get or put, the accessed entry moves to the end (most recently used).
    We can override removeEldestEntry to automatically evict the least recently used entry
    when size exceeds capacity.
    At its core:

    Override for LRU
    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size() > capacity; // evict eldest if size exceeds
    }


    This is why LinkedHashMap is perfect for LRU caches — you don’t have to manage eviction manually,
     just override this one method.*/

    private final int capacity;

    public LRUCacheUsingLinkedHashMap_2(int capacity) {
        // initialCapacity = capacity, loadFactor = 0.75, accessOrder = true
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
