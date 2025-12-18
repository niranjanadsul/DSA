package GreedyAlgorithm;

public class JumpGame_5 {
    //https://leetcode.com/problems/jump-game/
    /*You are given an integer array nums.
    You are initially positioned at the array's first index,
    and each element in the array represents your maximum jump length at that position.
    Return true if you can reach the last index, or false otherwise.

    Example 1:

    Input: nums = [2,3,1,1,4]
    Output: true
    Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
    Example 2:

    Input: nums = [3,2,1,0,4]
    Output: false
    Explanation: You will always arrive at index 3 no matter what.
    Its maximum jump length is 0, which makes it impossible to reach the last index.*/
    public boolean canJump(int[] nums) {
        int target = nums.length-1;
        int i=target-1;
        while(i>-1){
            if(i+nums[i]>=target)
                target=i;
            i--;
        }
        return target==0;
    }

    public static void main(String[] args) {
        JumpGame_5 jumpGame5=new JumpGame_5();
        System.out.println(jumpGame5.canJump(new int[]{2,3,1,1,4}));
        System.out.println(jumpGame5.canJump(new int[]{3,2,1,0,4}));
    }
}
