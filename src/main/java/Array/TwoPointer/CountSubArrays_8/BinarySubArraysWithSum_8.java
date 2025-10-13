package Array.TwoPointer.CountSubArrays_8;

public class BinarySubArraysWithSum_8 {
    //https://leetcode.com/problems/binary-subarrays-with-sum/description/
    /*Given a binary array nums and an integer goal,
    return the number of non-empty subarrays with a sum goal.
    A subarray is a contiguous part of the array.
    Example 1:

    Input: nums = [1,0,1,0,1], goal = 2
    Output: 4
    Explanation: The 4 subarrays are bolded and underlined below:
    [1,0,1,0,1]
    [1,0,1,0,1]
    [1,0,1,0,1]
    [1,0,1,0,1]
    Example 2:

    Input: nums = [0,0,0,0,0], goal = 0
    Output: 15*/
    public int numSubarraysWithSum(int[] nums, int goal) {
        //we will find # Subarrays with sum less than goal
        //then find the # Subarrays with sum less than goal-1
        //return the difference
        return findSubarraysLessThanEqualToSum(nums,goal)-findSubarraysLessThanEqualToSum(nums,goal-1);
    }

    public int findSubarraysLessThanEqualToSum(int[] nums, int goal){
        //if goal is negative then return 0 as # subarrays for negative goal is not possible
        if(goal<0)
            return 0;
        int i=0,j=0;
        int currentSum=0;
        int result = 0;
        while(j<nums.length){
            currentSum+=nums[j];
            //shrinking logic
            while(currentSum>goal){
                //we need to bring the currentSum <= goal
                //so we try to restore/shrink
                currentSum-=nums[i];
                i++;
            }
            //so now the current windows has sum <= goal
            //lets find out size of the window as it indicated the number of SubArrays ending at j
            result+=(j-i+1);
            j++;
        }
        return result;
    }
}
