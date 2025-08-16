package Graphs.ShortestPath_4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumMultiplicationsToReachEnd_11 {
    //https://www.geeksforgeeks.org/problems/minimum-multiplications-to-reach-end/1
    /*Given start, end and an array arr of n numbers.
    At each step, start is multiplied with any number in the array and then mod operation with 100000
    is done to get the new start.
    Your task is to find the minimum steps in which end can be achieved starting from start.
    If it is not possible to reach end, then return -1.

    Example 1:
    Input:
    arr[] = {2, 5, 7}
    start = 3, end = 30
    Output:
    2
    Explanation:
    Step 1: 3*2 = 6 % 100000 = 6
    Step 2: 6*5 = 30 % 100000 = 30

    Example 2:
    Input:
    arr[] = {3, 4, 65}
    start = 7, end = 66175
    Output:
    4
    Explanation:
    Step 1: 7*3 = 21 % 100000 = 21
    Step 2: 21*3 = 63 % 100000 = 63
    Step 3: 63*65 = 4095 % 100000 = 4095
    Step 4: 4095*65 = 266175 % 100000 = 66175*/

    int minimumMultiplications(int[] arr, int start, int end) {
        if(start==end)
            return 0;
        int mod = 100000;
        PriorityQueue<int[]> pq=new PriorityQueue<>(Comparator.comparingInt((int[] a)->a[0]));
        //as we mod with 100000 so nnumber of nodes will be from 0 to 9999
        int[] distance = new int[mod];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[start]=0;
                        //steps to reach node, node
        pq.add(new int[]{0,start});
        while(!pq.isEmpty()){
            int[] val = pq.remove();
            start= val[1];
            int step = val[0];
            for(int i:arr){
                int nextStart=(start*i)%mod;
                int nextStep = step+1;
                if(nextStep<distance[nextStart]) {
                    distance[nextStart]=nextStep;
                    if (nextStart == end)
                        return nextStep;
                    pq.add(new int[]{nextStep, nextStart});
                }
            }
        }
        return -1;
    }
}
