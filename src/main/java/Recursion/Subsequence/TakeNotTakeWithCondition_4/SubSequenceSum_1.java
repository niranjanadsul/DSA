package Recursion.Subsequence.TakeNotTakeWithCondition_4;

public class SubSequenceSum_1 {
    public static boolean checkSubsequenceSum(int N, int[] arr, int K) {
        // code here
        return subSeqSum(arr,0,K,0);
    }

    public static boolean subSeqSum(int[] arr,int index,int target, int sum){
        if(sum == target){
            return true;
        }
        if(index >= arr.length || sum>target){
            return false;
        }
        return subSeqSum(arr,index+1,target,sum+arr[index]) ||
                subSeqSum(arr,index+1,target,sum);
    }

    public static void main(String[] args) {
        System.out.println(checkSubsequenceSum(7,new int[]{10,1,2,7,6,1,5},8));
        System.out.println(checkSubsequenceSum(5,new int[]{2,3,5,7,9},100));
    }
}
