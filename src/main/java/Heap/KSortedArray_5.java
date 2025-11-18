package Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KSortedArray_5 {
    //https://www.geeksforgeeks.org/problems/k-sorted-array1610/1
    /*Given an array of n distinct elements.
    Check whether the given array is a k-sorted array or not.
    A k-sorted array is an array where each element is at most k distance away from
    its target position in the sorted array.
    Return "Yes" if the array is a k-sorted array else return "No".

    Examples

    Input: n=6, arr[] = {3, 2, 1, 5, 6, 4}, k = 2
    Output: Yes
    Explanation: Every element is at most 2 distance away from its target position in
    thesorted array.
    Input: n=7, arr[] = {13, 8, 10, 7, 15, 14, 12}, k = 1
    Output: No
    Expected Time Complexity: O(nlogn).
    Expected Auxiliary Space: O(n).*/

    //T.C=O(n * Log n)
    static class Pair{
        int value;
        int index;
        Pair(int value,int index){
            this.value=value;
            this.index=index;
        }
    }
    static String isKSortedArray(int arr[], int n, int k) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));
        for(int i=0;i<n;i++){
            pq.add(new Pair(arr[i],i));
        }
        int count=0;
        while(!pq.isEmpty()){
            Pair pair=pq.poll();
            if(Math.abs(pair.index-count)>k)
                return "No";
            count++;
        }
        return "Yes";
    }

    public static void main(String[] args) {
        System.out.println(isKSortedArray(new int[]{3, 2, 1, 5, 6, 4},6,2));//Yes
        System.out.println(isKSortedArray(new int[]{13, 8, 10, 7, 15, 14, 12},7,1));//No
    }
}
