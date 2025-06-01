package DynamicProgramming.DP_2D;

import java.util.Arrays;

public class GeeksTraining_1 {
    //https://www.geeksforgeeks.org/problems/geeks-training/1
    public int maximumPoints(int arr[][]) {
        // code here
        int[][] dp = new int[arr.length][arr[0].length];
        for(int[] i:dp){
            Arrays.fill(i,-1);
        }
        int max = Integer.MIN_VALUE;
        for(int i=0;i<arr[0].length;i++){
            max=Math.max(max,maxPoint(0,i,arr,dp));
        }
        return max;
    }

    //time complexity
    //total possible states are n * 3 hence O(n)
    public int maxPoint(int day, int task, int arr[][], int dp[][]){
        if(day==arr.length-1){
            return arr[day][task];
        }
        if(dp[day][task]!=-1){
            return dp[day][task];
        }

        int max = Integer.MIN_VALUE;
        for(int i=0;i<arr[0].length;i++){
            if(task!=i){
                max=Math.max(max, maxPoint(day+1,i,arr,dp));
            }
        }
        return dp[day][task]= arr[day][task]+max;
    }

    public static void main(String[] args) {
        GeeksTraining_1 geeksTraining1=new GeeksTraining_1();
        int max = geeksTraining1.maximumPoints(new int[][]{
                {1, 2, 5},
                {3, 1, 1},
                {3, 3, 3}
        });
        System.out.println(max);
    }
}
