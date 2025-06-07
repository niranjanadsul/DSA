package DynamicProgramming.Subsequence;

import java.util.Arrays;

public class SubsetSum_1 {
    static Boolean isSubsetSum(int arr[], int sum) {
        // code here
        int[][] dp = new int[arr.length][sum+1];
        for(int[] d:dp){
            Arrays.fill(d,-1);
        }
        return checkSum(0,sum,arr,dp);
    }

    public static boolean checkSum(int index, int target, int[] arr,int[][] dp){
        if(target==0)
            return true;
        if(index>=arr.length)
            return false;
        if(dp[index][target]!=-1){
            return dp[index][target]==1;
        }
        //not take
        boolean notTake = checkSum(index+1,target,arr,dp);
        //take
        boolean take =false;
        if(arr[index]<=target){
            take = checkSum(index+1,target-arr[index],arr,dp);
        }
        dp[index][target] = notTake || take?1:0;
        return notTake || take;
    }

    public static void main(String[] args) {
        boolean isSub=SubsetSum_1.isSubsetSum(new int[]{3, 34, 4, 12, 5, 2},9);//true
        System.out.println(isSub);
        isSub=SubsetSum_1.isSubsetSum(new int[]{3, 34, 4, 12, 5, 2},30);//false
        System.out.println(isSub);
    }
}
