package Heap;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.PriorityQueue;

public class KthSmallestElement_4 {
    //https://www.geeksforgeeks.org/problems/kth-smallest-element5635/1
    /*Given an integer array arr[] and an integer k,
    your task is to find and return the kth smallest element in the given array.

    Examples :

    Input: arr[] = [10, 5, 4, 3, 48, 6, 2, 33, 53, 10], k = 4
    Output: 5
    Explanation: 4th smallest element in the given array is 5.
    Input: arr[] = [7, 10, 4, 3, 20, 15], k = 3
    Output: 7
    Explanation: 3rd smallest element in the given array is 7.*/

    public int kthSmallest(int[] arr, int k) {
        //we will use max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        for(int n:arr){
            pq.add(n);
            if(pq.size()>k)
                pq.poll();
        }
        return pq.poll();
    }

    public static void main(String[] args) {
        KthSmallestElement_4 kthSmallestElement4=new KthSmallestElement_4();
        System.out.println(kthSmallestElement4.kthSmallest(new int[]{10, 5, 4, 3, 48, 6, 2, 33, 53, 10},4));//5
        System.out.println(kthSmallestElement4.kthSmallest(new int[]{7, 10, 4, 3, 20, 15},3));//7
    }
}
