package LinkedList.DoublyLinkedList;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

public class LFUCache_3 {
    //https://leetcode.com/problems/lfu-cache/description/
    /*Design and implement a data structure for a Least Frequently Used (LFU) cache.
    Implement the LFUCache class:

    LFUCache(int capacity) Initializes the object with the capacity of the data structure.
    int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
    void put(int key, int value) Update the value of the key if present, or inserts the key if not
    already present. When the cache reaches its capacity, it should invalidate and remove the
    least frequently used key before inserting a new item. For this problem,
    when there is a tie (i.e., two or more keys with the same frequency),
    the least recently used key would be invalidated.
    To determine the least frequently used key, a use counter is maintained for each key in the cache.
    The key with the smallest use counter is the least frequently used key.

    When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation).
    The use counter for a key in the cache is incremented either a get or put operation is called on it.
    The functions get and put must each run in O(1) average time complexity.



    Example 1:

    Input
    ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
    [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
    Output
    [null, null, null, 1, null, -1, 3, null, -1, 3, 4]

    Explanation
    // cnt(x) = the use counter for key x
    // cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
    LFUCache lfu = new LFUCache(2);
    lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
    lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
    lfu.get(1);      // return 1
                     // cache=[1,2], cnt(2)=1, cnt(1)=2
    lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
                     // cache=[3,1], cnt(3)=1, cnt(1)=2
    lfu.get(2);      // return -1 (not found)
    lfu.get(3);      // return 3
                     // cache=[3,1], cnt(3)=2, cnt(1)=2
    lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                     // cache=[4,3], cnt(4)=1, cnt(3)=2
    lfu.get(1);      // return -1 (not found)
    lfu.get(3);      // return 3
                     // cache=[3,4], cnt(4)=1, cnt(3)=3
    lfu.get(4);      // return 4
                     // cache=[4,3], cnt(4)=2, cnt(3)=3*/

    class Node{
        int key, value, freq;
        Node(int key,int v,int f){
            this.key=key;
            value=v;
            freq=f;
        }
    }

    Map<Integer,Node> keyToNode;//to ensure O(1) access
    Map<Integer, LinkedHashSet<Integer>> freqToKey; //stores keys in LRU fashion to use when frequency tie
    int capacity,minFreq;//to get the node with min freq when capacity becomes full

    public LFUCache_3(int capacity) {
        this.keyToNode = new HashMap<>();
        minFreq=0;
        this.capacity=capacity;
        this.freqToKey=new TreeMap<>();//store frequency in sorted order
    }


    public int get(int key) {
        Node ans = keyToNode.getOrDefault(key,null);
        if(ans==null)
            return -1;
        updateFrequencyRelatedInfo(key, ans);
        //return the ans
        return ans.value;
    }

    private void updateFrequencyRelatedInfo(int key, Node ans) {
        int currFreq= ans.freq;
        //need to increase the access frequency of this node in both maps
        int updatedFreq=currFreq+1;
        ans.freq=updatedFreq;

        //also update in frequency map
        freqToKey.get(currFreq).remove(key);
        if(freqToKey.get(currFreq).isEmpty())
            freqToKey.remove(currFreq);
        freqToKey.computeIfAbsent(updatedFreq,x->new LinkedHashSet<>()).add(key);

        updateMinFrequency();
    }

    private void updateMinFrequency() {
        //now update the value for min frequency
        minFreq=freqToKey.keySet().iterator().next();//first value will be the min
    }

    public void put(int key, int value) {
        if(keyToNode.containsKey(key)){
            //key already present so no need to worry about capacity
            //simply replace the value
            //and update the frequency info in both Node and frequency map
            keyToNode.get(key).value=value;
            updateFrequencyRelatedInfo(key,keyToNode.get(key));
        }else{
            //map does not contain this key and is completely a new key
            //need to also think of the capacity
            if(keyToNode.size()==capacity){
                //remove the LFU and LRU if LFU tie
                if(freqToKey.get(minFreq).size()>1){
                    //tie
                    int lruKey = freqToKey.get(minFreq).getFirst();//lru key will be at the head
                    freqToKey.get(minFreq).remove(lruKey);
                    keyToNode.remove(lruKey);
                }else{
                    //no tie, remove LFU
                    int lfuKey = freqToKey.get(minFreq).getFirst();
                    freqToKey.remove(minFreq);
                    keyToNode.remove(lfuKey);
                }
            }
            //add the new node to both the maps
            Node node = new Node(key,value,1);
            keyToNode.put(key,node);
            freqToKey.computeIfAbsent(1,x -> new LinkedHashSet<>()).add(key);
            // update the min frequency
            updateMinFrequency();
        }
    }
}
