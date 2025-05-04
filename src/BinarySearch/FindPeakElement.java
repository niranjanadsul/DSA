package BinarySearch;

import BinarySearch.IdentifyLeftRightSubArray.SingleElementInSortedArray;

public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int s=0, e = nums.length-1, mid, peak=-1;
        while(s<=e){
            mid = s+(e-s)/2;
            if((mid == 0 || nums[mid]>nums[mid-1]) && (mid == nums.length-1 || nums[mid]>nums[mid+1])){
                peak = mid;
                break;
            }
            else if(mid > 0 && nums[mid]<nums[mid-1]){
                e= mid -1;
            }
            else{
                s=mid+1;
            }
        }
        return peak;
    }

    public static void main(String[] args){
        FindPeakElement findPeakElement=new FindPeakElement();
        System.out.println(findPeakElement.findPeakElement(new int[]{1,2,1,3,5,6,4}));
        //in the above example there are 2 peaks
        //2 and 6
        //both answer ae correct
    }
}
