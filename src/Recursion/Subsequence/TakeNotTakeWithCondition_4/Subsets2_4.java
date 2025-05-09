package Recursion.Subsequence.TakeNotTakeWithCondition_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets2_4 {
    //https://leetcode.com/problems/subsets-ii/description/
    public List<List<Integer>> subsetsWithDup(int[] candidates) {
        List<List<Integer>> allCombinations= new ArrayList<>();
        Arrays.sort(candidates);
        findCombination(0,candidates,new ArrayList<>(),allCombinations);
        return allCombinations;
    }

    public void findCombination(int index, int[] candidates,
                                ArrayList<Integer> curr, List<List<Integer>> allCombinations){
        if(index>=candidates.length){
            allCombinations.add(new ArrayList<>(curr));
            return;
        }

        //take
        ArrayList<Integer> ls = new ArrayList<>(curr);
        ls.add(candidates[index]);
        findCombination(index+1,candidates,ls,allCombinations);
        ls.removeLast();

        //if we not take the current element
        //then we skip all its occurrances ahead
        int i = index+1;
        while(i<candidates.length){
            if(candidates[i]!=candidates[i-1])
                break;
            i++;
        }
        findCombination(i,candidates,new ArrayList<>(curr),allCombinations);
    }

    public static void main(String[] args) {
        Subsets2_4 subsets24=new Subsets2_4();
        System.out.println(subsets24.subsetsWithDup(new int[]{1,2,2}));//6
    }
}
