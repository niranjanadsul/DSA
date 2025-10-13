package Array.TwoPointer.CountSubArrays_8;

public class CountNumberOfNiceSubArrays_9 {
    //https://leetcode.com/problems/count-number-of-nice-subarrays/
    /*Given an array of integers nums and an integer k.
    A continuous subarray is called nice if there are k odd numbers on it.
    Return the number of nice sub-arrays.
    Example 1:

    Input: nums = [1,1,2,1,1], k = 3
    Output: 2
    Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
    Example 2:

    Input: nums = [2,4,6], k = 1
    Output: 0
    Explanation: There are no odd numbers in the array.
    Example 3:

    Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
    Output: 16*/
    public int numberOfSubarrays(int[] nums, int k) {
        //we will find # Subarrays with odd numbers less than goal
        //then find the # Subarrays with odd numbers less than goal-1
        //return the difference
        return findSubarraysHavingLessThanEqualToGoalOdds(nums,k)-findSubarraysHavingLessThanEqualToGoalOdds(nums,k-1);
    }
    public int findSubarraysHavingLessThanEqualToGoalOdds(int[] nums, int goal){
        //if goal is negative then return 0 as # subarrays for negative goal is not possible
        if(goal<0)
            return 0;
        int i=0,j=0;
        int currentSum=0;
        int result = 0;
        while(j<nums.length){
            if(nums[j]%2!=0)
                currentSum+=1;
            //shrinking logic
            while(currentSum>goal){
                //we need to bring the currentSum <= goal
                //so we try to restore/shrink
                if(nums[i]%2!=0)
                    currentSum-=1;
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
