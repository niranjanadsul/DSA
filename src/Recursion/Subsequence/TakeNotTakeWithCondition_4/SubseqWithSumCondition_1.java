package Recursion.Subsequence.TakeNotTakeWithCondition_4;

import java.util.Arrays;

public class SubseqWithSumCondition_1 {
    //https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/submissions/1623031018/
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        return countSubSet(0, Integer.MAX_VALUE, Integer.MIN_VALUE, target,nums);
    }

    public int countSubSet(int index, int min, int max, int target, int[] num){
        if(index>= num.length){
            if(min != Integer.MAX_VALUE && max != Integer.MIN_VALUE)
                return 1;
            else
                return 0;
        }

        int take = 0;
        if(Math.min(min, num[index])+Math.max(max,num[index])<=target){
            //take
            take = countSubSet(index+1,Math.min(min, num[index]),Math.max(max,num[index]),
                    target,num);
        }
        int notTake = 0;
        notTake = countSubSet(index+1, min, max, target,num);
        return take+notTake;
    }

    public static void main(String[] args) {
        SubseqWithSumCondition_1 subseqWithSumCondition1 = new SubseqWithSumCondition_1();
        int count= subseqWithSumCondition1.numSubseq(new int[]{3,5,6,7},9);//4
        count = subseqWithSumCondition1.numSubseq(new int[]{3,3,6,8},10);//6
        count = subseqWithSumCondition1.numSubseq(new int[]{2,3,3,4,6,7},12);//61
        count=subseqWithSumCondition1.numSubseq(new int[]{7,10,7,3,7,5,4},12);//56
        count=subseqWithSumCondition1.numSubseq(new int[]{14,4,6,6,20,8,5,6,8,12,6,10,14,9,17,16,9,7,14,11,14,15,13,11,10,18,13,17,17,14,17,7,9,5,10,13,8,5,18,20,7,5,5,15,19,14},22);
        System.out.println(count);
    }
}
