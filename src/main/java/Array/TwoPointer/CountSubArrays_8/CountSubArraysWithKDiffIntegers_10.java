package Array.TwoPointer.CountSubArrays_8;

import java.util.HashMap;

public class CountSubArraysWithKDiffIntegers_10 {
    //https://leetcode.com/problems/subarrays-with-k-different-integers/description/
    /*Given an integer array nums and an integer k, return the number of good subarrays of nums.
    A good array is an array where the number of different integers in that array is exactly k.
    For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
    A subarray is a contiguous part of an array.

    Example 1:

    Input: nums = [1,2,1,2,3], k = 2
    Output: 7
    Explanation: Subarrays formed with exactly 2 different integers:
    [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
    Example 2:

    Input: nums = [1,2,1,3,4], k = 3
    Output: 3
    Explanation: Subarrays formed with exactly 3 different integers:
    [1,2,1,3], [2,1,3], [1,3,4].*/
    public int subarraysWithKDistinct(int[] nums, int k) {
        //we will find # Subarrays with distinct numbers less than goal
        //then find the # Subarrays with distinct numbers less than goal-1
        //return the difference
        return subarraysWithlessThanEqualKDistinct(nums,k)-subarraysWithlessThanEqualKDistinct(nums,k-1);
    }

    /**windows size indicates the number of SubArrays ending at j satisfying the goal
     */
    public int subarraysWithlessThanEqualKDistinct(int[] nums, int goal){
        //if goal is negative then return 0 as # subarrays for negative goal is not possible
        if(goal<0)
            return 0;
        int i=0,j=0; //pointer

        HashMap<Integer,Integer> mp=new HashMap<>(); //conditional artifact
        int result = 0; //answer to return
        while(j<nums.length){
            //process the current element at pointer j
            int curr=nums[j];
            mp.compute(curr,(key,v)->v==null?1:v+1);

            //shrinking logic
            while(mp.size()>goal){
                //we need to bring the map size <= goal
                //so we try to restore/shrink
                mp.compute(nums[i],(key,v)->v-1);
                if(mp.get(nums[i])==0)
                    mp.remove(nums[i]);
                i++;
            }
        /**
         * so now the current windows has distinct <= goal
         * lets find out size of the window as it indicates the number of SubArrays ending at j
         * */
            result+=(j-i+1);
            j++;
        }
        return result;
    }
}
