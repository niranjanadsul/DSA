package DynamicProgramming.DPSubsequence.MeetInTheMiddle;

import java.util.*;

public class PartitionWithMinimumSumDifference_3 {
    //https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/
    //split into left and right array with n and n elements
    //target sum  = total sum/2
    //create maps of count and sums for both left and right arrays
    //then iterate each key from left map and choose appropriate key form right map such that
    // leftKey + rightkey = n
    // for each such iteration calculate the absolute difference and then find the min difference
    public int minimumDifference(int[] nums) {

        int n=nums.length;
        int rlength=n-n/2;
        int totalSum=0;
        for(int i=0;i<nums.length;i++){
            totalSum+=nums[i];
        }
        int goal=totalSum/2;

        int[] leftArray=new int[n/2];
        int[] rightArray=new int[rlength];

        splitArrayMiddle(nums,leftArray,rightArray);

        HashMap<Integer,List<Integer>>leftMap=new HashMap<>();
        subsetMap(0, leftArray, leftMap, 0,0);
        HashMap<Integer,List<Integer>>rightMap=new HashMap<>();
        subsetMap(0, rightArray, rightMap, 0,0);

        return closestSubsequences(leftMap,rightMap,goal,totalSum,n/2);


    }

    public int closestSubsequences(HashMap<Integer,List<Integer>>leftMap,HashMap<Integer,
            List<Integer>>rightMap, int goal,int totalSum,int n){
        int min=Integer.MAX_VALUE;

        for(int key:leftMap.keySet()){
            List<Integer>leSumList=leftMap.get(key);
            int rightKey=n-key;
            List<Integer>rightSum=rightMap.get(rightKey);
            Collections.sort(rightSum);
            for(int i=0;i<leSumList.size();i++){
                int x=leSumList.get(i);
                int target=goal-x;
                int y=binarySearchNearestElement(rightSum,target);
                int sumdi=x+rightSum.get(y);
                min=Math.min(min,Math.abs((2*sumdi)-totalSum));
            }
        }
        return min;
    }

    public static int binarySearchNearestElement(List<Integer> arr,int target) {
        int start=0,end=arr.size()-1;
        while(start<end){
            int mid=start+(end-start)/2;

            if(arr.get(mid)==target){
                return mid;
            }
            if(target>arr.get(mid)){
                start=mid+1;
            }else{
                end=mid;
            }
        }
        return start;
    }

    public  void subsetMap(int i, int[]arr, HashMap<Integer, List<Integer>> map, int sum, int count) {
        if(i>=arr.length) {
            map.computeIfAbsent(count,x->new ArrayList<>()).add(sum);
            return;
        }
        subsetMap(i+1,arr,map,sum+arr[i],count+1);
        subsetMap(i+1,arr,map,sum,count);
    }

    public static void splitArrayMiddle(int[] arr,int[] leftArray,int[] rightArray) {
        int n=arr.length;
        for(int i=0;i<n/2;i++) {
            leftArray[i]=arr[i];
        }

        int l=0;
        for(int j=leftArray.length;j<n;j++) {
            rightArray[l]=arr[j];
            l++;
        }
    }

    public static void main(String[] args) {
        PartitionWithMinimumSumDifference_3 partitionWithMinimumSumDifference3=
                new PartitionWithMinimumSumDifference_3();
        int min = partitionWithMinimumSumDifference3.minimumDifference(new int[]{2,-1,0,4,-2,-9});//0
        System.out.println(min);
        min = partitionWithMinimumSumDifference3.minimumDifference(new int[]{-36,36});//72
        System.out.println(min);
        min = partitionWithMinimumSumDifference3.minimumDifference(new int[]{3,9,7,3});//2
        System.out.println(min);
    }
}
