package DynamicProgramming.DP_1D;

public class ClimbingStairs_1 {
    //https://leetcode.com/problems/climbing-stairs/submissions/837988155/
    // to reach n th step we can consider number of ways to n-1 th step
    // and number of ways to reach n-2 th step as we can only move 1 or 2 steps
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0]=1;
        dp[1]=1;
        int i = 2;
        while(i<dp.length){
            dp[i] = dp[i-1]+dp[i-2];
            i++;
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        ClimbingStairs_1 climbingStairs1 = new ClimbingStairs_1();
        int ways = climbingStairs1.climbStairs(3);
        System.out.println(ways);
    }
}
