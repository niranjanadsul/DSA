package Array.TwoPointer;

public class MaxConsecutiveOnes3_5 {
    //https://leetcode.com/problems/max-consecutive-ones-iii/description/
    /*Given a binary array nums and an integer k,
    return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
    Example 1:

    Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
    Output: 6
    Explanation: [1,1,1,0,0,1,1,1,1,1,1]
    Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
    Example 2:

    Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
    Output: 10
    Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
    Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.*/
    public int longestOnes(int[] nums, int k) {
        int consec=k;
        int i=0,j=0;
        int len = 0;
        while(j<nums.length){
            if(nums[j]==1){
                j++;
            }else{
                //arr[j] is 0
                //check if we can convert it to 1
                if(consec>0){
                    consec--;
                    j++;
                }else{
                    //already we have converted k 0's to 1
                    //we first need to restore atleast one 0 to 1
                    while(consec!=1){
                        if(nums[i]==0)
                            consec++;
                        i++;
                    }
                }
            }
            len=Math.max(len,j-i);
        }
        return len;
    }
}
