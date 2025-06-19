package DynamicProgramming.LIS_6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestDivisibleSubsequence_4 {
    //this is same as LIS just the take condition is different
    //take condition has mod operator condition
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int[][] dp = new int[nums.length][nums.length+1];
        Arrays.sort(nums);
        //fill 1st column
        //as the prev index cannot be same or smaller than current index i
        for(int index =0;index<nums.length;index++){
            dp[index][0]=0;
        }
        //fill 1st row
        //we will use the take condition of prev == nums.length
        for(int prev=0;prev<=nums.length;prev++){
            //as the array is sorted the prev element will be greater then current
            if(prev == nums.length || nums[prev]%nums[0]==0)
                dp[0][prev]=1;
            else
                dp[0][prev]=0;
        }
        for(int index =1;index<nums.length;index++){
            for(int prev=1;prev<=nums.length;prev++){
                //take
                int take = Integer.MIN_VALUE;
                //when prev = n means no other number is yet taken
                //and this is the first number
                //as the array is sorted the prev element will be greater then current
                if(prev==nums.length || nums[prev]%nums[index]==0){
                    take = 1+dp[index-1][index];
                }
                int notTake = dp[index-1][prev];
                dp[index][prev]=Math.max(take,notTake);
            }
        }

        ArrayList<Integer> ans=new ArrayList<>();
        int n=nums.length,i = n-1,j=n;
        while(i>0){// as i-1 will give index out of bound exception
            if(dp[i][j]==dp[i-1][j]){
                i--;
            }else if(dp[i][j]==1+dp[i-1][i]){
                ans.addFirst(nums[i]);
                j=i;
                i--;
            }
        }
        if(i==0 && dp[i][j]==1)
            ans.addFirst(nums[i]);
        return ans;
    }
}
