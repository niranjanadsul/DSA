package BinarySearch;

public class CeilInSortedArray {
    public static int ceil(int[] num, int target){
        int start=0,end=num.length-1;
        int ceiling=Integer.MAX_VALUE;
        while(start<=end){
            int mid = start+(end-start)/2;
            if(num[mid]==target)
                return target;
            if(num[mid]>target) {
                ceiling=Math.min(num[mid],ceiling);
                end=mid-1;
            }else{
                start=mid+1;
            }

        }
        if(ceiling==Integer.MAX_VALUE)
            ceiling=-1;
        return ceiling;
    }
}
