package DynamicProgramming.DPMatrixChainMul_7;

import java.util.Arrays;

public class MinCostToCutStick_2 {
    //https://leetcode.com/problems/minimum-cost-to-cut-a-stick/description/
    //TC = O(cut.length * cut.length)
    // we cannot use n as dimension for dp as n can have higher values such as 8100 to reach memory limit
    //whereas cuts array has length up to 100 only
    //so design the solution to use cuts array

    int minCost(int n, int[] cuts) {
        int[][] dp = new int[cuts.length][cuts.length];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        Arrays.sort(cuts);
        return solve(0, cuts.length - 1,0, n, cuts, dp);
    }

    int solve(int left, int right,int startStick, int endStick, int[] cuts,  int[][] dp) {
        if (left > right) return 0;

        if (dp[left][right] != -1) return dp[left][right];

        int cost = Integer.MAX_VALUE;

        for (int i = left; i <= right; i++) {
            int k=cuts[i];
            int left_cost = solve(left, i - 1,startStick, k, cuts, dp);
            int right_cost = solve(i + 1, right, k, endStick, cuts, dp);
            int curr_cost = (endStick - startStick) + left_cost + right_cost;
            cost = Math.min(cost, curr_cost);
        }

        return dp[left][right] = cost;
    }

    public static void main(String[] args) {
        MinCostToCutStick_2 minCostToCutStick2=new MinCostToCutStick_2();
        System.out.println(minCostToCutStick2.minCost(7,new int[]{1,3,4,5}));//16
        System.out.println(minCostToCutStick2.minCost(9,new int[]{5,6,1,4,2}));//22
    }
}
