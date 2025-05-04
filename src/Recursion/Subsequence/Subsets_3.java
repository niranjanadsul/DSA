package Recursion.Subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Subsets_3 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> allSubSets = new ArrayList<>();
        findSubset(nums,new ArrayList<>(),0,allSubSets);
        return allSubSets;
    }

    public void findSubset(int[] nums,List<Integer> curr, int index,List<List<Integer>> allSubSets){
        if(index>=nums.length){
            allSubSets.add(curr);
            return;
        }
        List<Integer> a = new ArrayList<>(curr);
        a.add(nums[index]);
        findSubset(nums,a,index+1,allSubSets);
        findSubset(nums,curr,index+1,allSubSets);
    }

    public static void main(String[] args) {
        Subsets_3 subsets3 = new Subsets_3();
        List<List<Integer>> a= subsets3.subsets(new int[]{1,2,3});
        System.out.println();
    }
}
