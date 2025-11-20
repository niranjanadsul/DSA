package Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class ReplaceElementByRank_7 {
    //https://www.geeksforgeeks.org/problems/replace-elements-by-its-rank-in-the-array/1
    /*Given an array arr of N integers,
    the task is to replace each element of the array by its rank in the array.
    The rank of an element is defined as the distance between the element with the
    first element of the array when the array is arranged in ascending order.
    If two or more are same in the array then their rank is also the same as the rank of
    the first occurrence of the element.

        Example 1:

        Input:
        N = 6
        arr = [20, 15, 26, 2, 98, 6]
        Output:
        4, 3, 5, 1, 6, 2
        Explanation:
        After sorting, array becomes {2,6,15,20,26,98}
        Rank(2) = 1 (at index 0)
        Rank(6) = 2 (at index 1)
        Rank(15) = 3 (at index 2)
        Rank(20) = 4 (at index 3) and so on..
        Example 2:

        Input:
        N = 4
        arr = [2, 2, 1, 6]
        Output:
        2, 2, 1, 3
        Explanation:
        After sorting, array becomes {1, 2, 2, 6}
        Rank(1) = 1 (at index 0)
        Rank(2) = 2 (at index 1)
        Rank(2) = 2 (at index 2)
        Rank(6) = 3 (at index 3)
        Rank(6) = 3 because rank after 2 is 3 as rank
        of same element remains same and for next element
        increases by 1.*/

    //TC= O( n * Log n)
    static class Pair{
        int value;
        int index;
        Pair(int value,int index){
            this.value=value;
            this.index=index;
        }
    }

    static int[] replaceWithRank(int arr[], int N) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a->a.value));
        for(int i=0;i<N;i++){
            pq.add(new Pair(arr[i],i));
        }
        HashSet<Integer> hs= new HashSet<>();
        int rank=0;
        while(!pq.isEmpty()){
            Pair curr=pq.poll();
            if(!hs.contains(curr.value)){
                hs.add(curr.value);
                rank++;
            }
            arr[curr.index]=rank;
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(replaceWithRank(new int[]{20, 15, 26, 2, 98, 6}, 6)));//4, 3, 5, 1, 6, 2
        System.out.println(Arrays.toString(replaceWithRank(new int[]{2,2,1,6}, 4)));//2,2,1,3
    }
}
