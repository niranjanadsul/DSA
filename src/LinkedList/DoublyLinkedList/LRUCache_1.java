package LinkedList.DoublyLinkedList;

import java.util.HashMap;
import java.util.Map;

public class LRUCache_1 {
    //https://leetcode.com/problems/lru-cache/description/
    /*Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
    Implement the LRUCache class:

    LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
    int get(int key) Return the value of the key if the key exists, otherwise return -1.
    void put(int key, int value) Update the value of the key if the key exists.
    Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity
    from this operation, evict the least recently used key.
    The functions get and put must each run in O(1) average time complexity.
    Example 1:

    Input
    ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
    [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
    Output
    [null, null, null, 1, null, -1, null, -1, 3, 4]

    Explanation
    LRUCache lRUCache = new LRUCache(2);
    lRUCache.put(1, 1); // cache is {1=1}
    lRUCache.put(2, 2); // cache is {1=1, 2=2}
    lRUCache.get(1);    // return 1
    lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    lRUCache.get(2);    // returns -1 (not found)
    lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    lRUCache.get(1);    // return -1 (not found)
    lRUCache.get(3);    // return 3
    lRUCache.get(4);    // return 4*/

    /*
    * We need:
    O(1) lookup → use a HashMap (key → Node)
    O(1) eviction of least recently used → maintain a Doubly Linked List (DLL)
    Most recently used = head
    Least recently used = tail
    On every get or put, move the node to the front.

    DLL Operations
    Add node to front (mark as most recently used).
    Remove node from list (for eviction or update).
    Remove node from tail (least recently used).*/

    class Node{
        int key,value;//keeping key in the node will help us remove the LRU from map also
        Node previous;
        Node next;
    }

    private int capacity;
    private Map<Integer,Node> map;
    private Node head,tail;
    public LRUCache_1(int capacity) {
        this.capacity = capacity;
        map=new HashMap<>();
        head=null;
    }

    public int get(int key) {
        Node node=map.getOrDefault(key,null);
        if(node==null)
            return -1;
        removeAndPlaceAtFront(node);
        return node.value;
    }

    private void removeAndPlaceAtFront(Node node) {
        //put the key at the head of DLL if not already to indicate it was Most Recently Accessed
        if(head!= node){
            node.previous.next= node.next;
            if(node.next!=null)
                node.next.previous= node.previous;
            else//node is tail
                tail= node.previous;
            node.next=head;
            node.next.previous= node;
            head= node;
        }
    }

    public void put(int key, int value) {
        //if the key exists in the map then change the value of the node
        //and place that node in the front
        if(map.containsKey(key)){
            Node node =map.get(key);
            node.value=value;
            removeAndPlaceAtFront(node);
        }else{
            //map does not contain key
            //if map is full by capacity then remove the LRU node from DLL and map
            if(map.size()==capacity){
                Node node=tail;
                if(node!=head){
                    tail=node.previous;
                    tail.next=null;
                }else{
                    head=null;
                    tail=null;
                }
                map.remove(node.key);
            }
            //Add the new node to front of DLL and map
            Node node = new Node();
            node.key=key;
            node.value=value;

            map.put(key,node);

            if(head==null){
                tail=node;
            }else {
                node.next=head;
                head.previous=node;
            }
            head=node;
        }

    }
}
