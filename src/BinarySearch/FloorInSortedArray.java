package BinarySearch;

public class FloorInSortedArray {
    public static int floor(int[] num, int target){
        int start=0,end=num.length-1;
        int floor=-1;
        while(start<=end){
            int mid = start+(end-start)/2;
            if(num[mid]==target)
                return target;
            if(num[mid]<target) {
                floor=Math.max(num[mid],floor);
                start=mid+1;
            }else{
                end=mid-1;
            }

        }
        return floor;
    }

    public static void main(String[] args){
        int floor,ceil;
        floor=floor(new int[]{1, 2, 8, 10, 10, 12, 19},11);
        System.out.println(floor);

        floor=floor(new int[]{3, 4, 7, 8, 8, 10},5);
        ceil = CeilInSortedArray.ceil(new int[]{3, 4, 7, 8, 8, 10},5);
        System.out.println(floor+" "+ceil);

        floor=floor(new int[]{3, 4, 4, 7, 8, 10},8);
        ceil = CeilInSortedArray.ceil(new int[]{3, 4, 4, 7, 8, 10},8);
        System.out.println(floor+" "+ceil);
    }
}
