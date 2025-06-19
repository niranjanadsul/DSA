package DynamicProgramming.LIS_6;

import java.util.Arrays;
import java.util.Comparator;

public class LongestStringChain_5 {
    //https://leetcode.com/problems/longest-string-chain/description/
    //same as LIS
    //different take condition
    public int longestStrChain(String[] nums) {
        int[][] dp = new int[nums.length][nums.length+1];
        //sort arrays based on string length
        Arrays.sort(nums, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });

        //fill 1st column
        //as the prev index cannot be same or smaller than current index i
        for(int current =0;current<nums.length;current++){
            dp[current][0]=0;
        }
        //fill 1st row
        //we will use the take condition of isPredecessor
        for(int prev=1;prev<=nums.length;prev++){
            if(prev == nums.length || isPredecessor(nums[0],nums[prev]))
                dp[0][prev]=1;
        }
        for(int current =1;current<nums.length;current++){
            for(int prev=1;prev<=nums.length;prev++){
                //take
                int take = Integer.MIN_VALUE;
                //when prev = n means no other number is yet taken
                //and this is the first number
                if(prev==nums.length || isPredecessor(nums[current],nums[prev])){
                    take = 1+dp[current-1][current];
                }
                int notTake = dp[current-1][prev];
                dp[current][prev]=Math.max(take,notTake);
            }
        }
        return dp[nums.length-1][nums.length];
    }

    public boolean isPredecessor(String current,String prev){
        //prev string should be greater than current by 1 character
        if(current.length()+1!=prev.length())
            return false;
        int i=0,j=0;
        //e.g cba bcad
        while(j<prev.length()){//until we reach the end of prev string as prev is larger
            if(i<current.length() && current.charAt(i)==prev.charAt(j)){
                //current str pointer might reach end earlier as current string is shorter
                i++;
                j++;
            }else{
                j++;
            }
        }
        return i== current.length() && j==prev.length();
    }
}
