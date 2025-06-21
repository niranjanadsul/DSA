package DynamicProgramming.LIS_6;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestBitonicSubsequence_6 {
    //https://www.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1
    public static int LongestBitonicSequence(int n, int[] nums) {
        int[] lenOfLISUpToEachIndex = getLIS(nums);
        System.out.println(Arrays.toString(lenOfLISUpToEachIndex));

        int[] revNum = reverseArray(nums);
        int[] lenOfLISAtEachIndexOfReverseArray = getLIS(revNum);
        int[] lenOfLDSFromEachIndexOfArray = reverseArray(lenOfLISAtEachIndexOfReverseArray);
        System.out.println(Arrays.toString(lenOfLDSFromEachIndexOfArray));

        int max = 0;
        for(int i=0;i<nums.length;i++){
            //if lenOfLISUpToEachIndex[i] is 1 then it indicates that only current element is LIS
            //means there might only be LDS which is not a bitonic seq
            //if lenOfLDSFromEachIndexOfArray[i] is 2 then it indicates that only current element is LDS
            //means there might only be LIS which is not a bitonic seq
            if(lenOfLISUpToEachIndex[i]==1 || lenOfLDSFromEachIndexOfArray[i]==1)
                continue;
            max = Math.max(lenOfLISUpToEachIndex[i]+lenOfLDSFromEachIndexOfArray[i]-1,max);
        }
        return max;
    }

    public static int[] reverseArray(int[] nums){
        int[] revNum = new int[nums.length];
        int j=nums.length-1;
        for(int i=0;i<nums.length;i++){
            revNum[j--]=nums[i];
        }
        return revNum;
    }

    public static int[] getLIS(int nums[]) {
        ArrayList<Integer> dpLIS = new ArrayList<>();
        int[] lenOfLISAtEachIndex = new int[nums.length];
        for(int i=0;i<nums.length;i++){ //TC n
            int num = nums[i];
            if(dpLIS.isEmpty() || dpLIS.get(dpLIS.size()-1)<num) {
                dpLIS.add(num);
                lenOfLISAtEachIndex[i]=dpLIS.size();
            }else {
                int lb=floor(dpLIS,num); // TC log n
                if(lb!=-1 && dpLIS.get(lb)==num){
                    lenOfLISAtEachIndex[i]=lb+1;
                    continue;
                }
                lenOfLISAtEachIndex[i]=lb+2;//length after including current num
                dpLIS.remove(lb+1);
                dpLIS.add(lb+1,num);
            }
        }
        //System.out.println(Arrays.toString(lenOfLISAtEachIndex));
        return lenOfLISAtEachIndex;
    }

    public static int floor(ArrayList<Integer> num, int target){
        int start=0,end=num.size()-1;
        int floor=-1;
        while(start<=end){
            int mid = start+(end-start)/2;
            if(num.get(mid)==target)
                return mid;
            if(num.get(mid)<target) {
                if(floor==-1)
                    floor=mid;
                else if(num.get(mid)>num.get(floor))
                    floor=mid;
                start=mid+1;
            }else{
                end=mid-1;
            }

        }
        return floor;
    }

    public static void main(String[] args) {
        System.out.println(LongestBitonicSubsequence_6.LongestBitonicSequence(8,new int[]{1,11,2,10,4,5,2,1}));//6
        System.out.println(LongestBitonicSubsequence_6.LongestBitonicSequence(8,new int[]{1,4,2,7,9,10}));//3
    }
}
