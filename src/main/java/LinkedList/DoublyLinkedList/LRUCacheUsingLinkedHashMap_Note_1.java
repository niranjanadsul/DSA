package LinkedList.DoublyLinkedList;

public class LRUCacheUsingLinkedHashMap_Note_1 {
    /**
    *       LinkedHashMap = HashMap + Doubly Linked List.
     *
     *     👉 That means:
     *     HashMap part → gives O(1) average lookup, insertion, deletion (by hashing).
     *     LinkedList part → keeps track of order of entries (either insertion order or access order).
     *     Each entry in LinkedHashMap is a node that contains:
     *
     *     class Entry<K,V> extends HashMap.Node<K,V> {
     *         Entry<K,V> before, after; // doubly-linked list pointers
     *     }
     *
     *     So every time you add a key-value:
     *     It goes into the HashMap bucket (like normal).
     *     Also gets linked into a doubly linked list for order tracking.
     *
     *     Ordering Modes
     *
     *     LinkedHashMap can maintain order in two ways:
     *     Insertion Order (default)
     *     Iteration happens in the order keys were inserted.
     *
     *     Access Order (accessOrder=true)
     *     Each get() or put() moves that entry to the end of the list (most recently used).
     *
     *     Perfect for LRU cache.
     *
     *     Constructor
     *     LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)
     *
     *     initialCapacity: starting buckets in HashMap.
     *     loadFactor: threshold before resizing.
     *     accessOrder: false → insertion order, true → access order.*
     *
     *
     *     removeEldestEntry is the magic hook that makes LinkedHashMap work like an LRU cache.
     *
         * 🔹 Default Implementation
         * In LinkedHashMap, the default implementation of removeEldestEntry is:
         *
         * protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
         *     return false;
         * }
     *      By default → it never removes automatically.
     * So a plain LinkedHashMap behaves just like a HashMap with ordering (insertion or access),
     * but it won’t evict anything unless you override this method.
     * Called after every put().
     * The eldest parameter refers to the first entry in the linked list
     * (least recently inserted or least recently accessed, depending on accessOrder
     * */
}
