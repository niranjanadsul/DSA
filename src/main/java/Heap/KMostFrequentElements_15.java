package Heap;

import java.util.*;

public class KMostFrequentElements_15 {
    //https://leetcode.com/problems/top-k-frequent-elements/description/
    /*Given an integer array nums and an integer k,
    return the k most frequent elements. You may return the answer in any order.

    Example 1:
    Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1,2]

    Example 2:
    Input: nums = [1], k = 1
    Output: [1]

    Example 3:
    Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
    Output: [1,2]*/
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map= new HashMap<>();
        for(int num:nums){
            map.compute(num,(key,v)->v==null?1:v+1);
        }
        List<Integer>[] frequency = new List[nums.length+1];
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if(frequency[entry.getValue()]==null){
                List<Integer> ls = new ArrayList<>();
                ls.add(entry.getKey());
                frequency[entry.getValue()]=ls;
            }else{
                frequency[entry.getValue()].add(entry.getKey());
            }
        }
        int[] ans = new int[k];
        for(int i=frequency.length-1,j=0;i>=0 && j<k;i--){
            if(frequency[i]!=null){
                for(int ele:frequency[i]){
                    ans[j++]=ele;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        KMostFrequentElements_15 kMostFrequentElements15=new KMostFrequentElements_15();
        System.out.println(Arrays.toString(kMostFrequentElements15.topKFrequent(new int[]{1,1,1,2,2,3},2)));//1,2
    }
}
