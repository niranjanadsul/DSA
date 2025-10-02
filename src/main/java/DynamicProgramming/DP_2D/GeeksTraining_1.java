package DynamicProgramming.DP_2D;

import java.util.Arrays;

public class GeeksTraining_1 {
    //https://www.geeksforgeeks.org/problems/geeks-training/1
    /*Geek is going for a training program for n days. He can perform any of these activities:
    Running, Fighting, and Learning Practice.
    Each activity has some point on each day.
    As Geek wants to improve all his skills, he can't do the same activity on two consecutive days.
    Given a 2D array arr[][] of size n where arr[i][0], arr[i][1], and arr[i][2] represent
    the merit points for Running, Fighting, and Learning on the i-th day,
    determine the maximum total merit points Geek can achieve .
    Example:
    Input: arr[]= [[1, 2, 5], [3, 1, 1], [3, 3, 3]]
    Output: 11
    Explanation: Geek will learn a new move and earn 5 point then on second day he will do running
    and earn 3 point and on third day he will do fighting and earn 3 points so, maximum merit point
    will be 11.*/

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
