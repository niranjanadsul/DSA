package Heap;

import java.util.PriorityQueue;

public class KthLargestElement_3 {
    //https://leetcode.com/problems/kth-largest-element-in-an-array/
    /*Given an integer array nums and an integer k,
    return the kth largest element in the array.
    Note that it is the kth largest element in the sorted order,
    not the kth distinct element.
    Can you solve it without sorting?
    Example 1:

    Input: nums = [3,2,1,5,6,4], k = 2
    Output: 5
    Example 2:

    Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
    Output: 4*/
    //we will use min heap i.e. priority queue
    //aim is to keep only k elements in the queue so that the element at
    //front will be the k th largest element
    //T.C. = O(n * log n)
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        //min heap
        for(int n:nums){
            pq.add(n);
            if(pq.size()>k)
                pq.poll();
        }
        return pq.poll();
    }

    public static void main(String[] args) {
        KthLargestElement_3 kthLargestElement3=new KthLargestElement_3();
        System.out.println(kthLargestElement3.findKthLargest(new int[]{3,2,1,5,6,4},2));//5
        System.out.println(kthLargestElement3.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6},4));//4
        System.out.println(kthLargestElement3.findKthLargest(new int[]{-1,2,0},3));//-1
    }
}
