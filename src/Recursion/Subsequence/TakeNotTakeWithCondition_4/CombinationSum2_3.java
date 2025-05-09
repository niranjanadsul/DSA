package Recursion.Subsequence.TakeNotTakeWithCondition_4;

import java.util.*;
import java.util.stream.Collectors;

public class CombinationSum2_3 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> allCombinations= new ArrayList<>();
        Arrays.sort(candidates);
        findCombination(0,target,candidates,new ArrayList<>(),allCombinations);
        return allCombinations;
    }

    public void findCombination(int index, int target, int[] candidates,
                                ArrayList<Integer> curr, List<List<Integer>> allCombinations){
        if(target==0){
            allCombinations.add(new ArrayList<>(curr));
            return;
        }
        if(index >= candidates.length)
            return;
        if(candidates[index]<=target) {//take condition to generate all combinations of current element
            ArrayList<Integer> ls = new ArrayList<>(curr);
            ls.add(candidates[index]);
            findCombination(index+1,target-candidates[index],candidates,ls,allCombinations);
            ls.removeLast();
        }else{
            //as the array is sorted if curr element is greater then target then all
            // further elements will be greater
            return;
        }

        //if we not take the current element
        //then we skip all its occurrances ahead
        int i = index+1;
        while(i<candidates.length){
            if(candidates[i]!=candidates[i-1])
                break;
            i++;
        }
        findCombination(i,target,candidates,new ArrayList<>(curr),allCombinations);
    }

    public static void main(String[] args) {
        CombinationSum2_3 combinationSum1_2= new CombinationSum2_3();
        List<List<Integer>> allCombinations = combinationSum1_2.combinationSum(new int[]{10,1,2,7,6,1,5},8);
        System.out.println(allCombinations.size());//4
        allCombinations = combinationSum1_2.combinationSum(new int[]{2,5,2,1,2},5);
        System.out.println(allCombinations.size());//2
        allCombinations = combinationSum1_2.combinationSum(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
                ,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1},30);
        System.out.println(allCombinations.size());
    }
}
