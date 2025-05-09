package Recursion.Subsequence.TakeNotTakeWithCondition_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum3_5 {
    //https://leetcode.com/problems/combination-sum-iii/
    public List<List<Integer>> combinationSum3(int k, int target) {
        List<List<Integer>> allCombinations= new ArrayList<>();
        findCombination(0,k,target,
                new int[]{1,2,3,4,5,6,7,8,9},new ArrayList<>(),allCombinations);
        return allCombinations;
    }

    public void findCombination(int index, int k, int target,int[] candidates,
                                ArrayList<Integer> curr, List<List<Integer>> allCombinations){
        if(curr.size()==k){
            if(target==0)
                allCombinations.add(new ArrayList<>(curr));
            return;
        }
        if(target==0 || index>=candidates.length){
            return;
        }

        //take
        ArrayList<Integer> ls = new ArrayList<>(curr);
        ls.add(candidates[index]);
        findCombination(index+1,k,target-candidates[index],
                candidates,ls,allCombinations);
        ls.removeLast();

        //if we not take the current element
        findCombination(index+1,k,target,candidates,new ArrayList<>(curr),allCombinations);
    }

    public static void main(String[] args) {
        CombinationSum3_5 combinationSum35=new CombinationSum3_5();
        System.out.println(combinationSum35.combinationSum3(3,7));//1
        System.out.println(combinationSum35.combinationSum3(3,9));//3
    }
}
