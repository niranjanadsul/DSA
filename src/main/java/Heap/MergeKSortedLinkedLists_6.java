package Heap;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedLinkedLists_6 {
    //https://leetcode.com/problems/merge-k-sorted-lists/description/
    /*You are given an array of k linked-lists lists,
    each linked-list is sorted in ascending order.
    Merge all the linked-lists into one sorted linked-list and return it.
    Example 1:

    Input: lists = [[1,4,5],[1,3,4],[2,6]]
    Output: [1,1,2,3,4,4,5,6]
    Explanation: The linked-lists are:
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    merging them into one sorted linked list:
    1->1->2->3->4->4->5->6
    Example 2:

    Input: lists = []
    Output: []
    Example 3:

    Input: lists = [[]]
    Output: []*/
    //if number of nodes in the array of lists is n
    //then time complexity is O( n * log n)
    //for each node need to be added to the min heap which needs log n time for minHeapify
    public static class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head=null,tail=null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for(ListNode l:lists){
            while(l!=null){
                ListNode curr = l;
                l=l.next;
                pq.add(curr);
                curr.next=null;
            }
        }

        while(!pq.isEmpty()){
            ListNode node = pq.poll();
            if(head!=null){
                tail.next=node;
                tail=node;
            }else{
                head=node;
                tail=node;
            }
        }
        return head;
    }

}
