package BinarySearch;

public class SearchInsertPosition {

    public static int searchInsertPos(int[] num, int target){
        int start=0,end=num.length-1;
        int floor=-1;
        while(start<=end){
            int mid = start+(end-start)/2;
            if(num[mid]==target)
                return mid;
            if(num[mid]<target) {
                floor=floor==-1 || num[mid]>num[floor]?mid:floor;
                start=mid+1;
            }else{
                end=mid-1;
            }

        }
        return floor+1;
    }

    public static void main(String[] args){
        int floor;
        floor=searchInsertPos(new int[]{1,3,5,6},7);
        System.out.println(floor);
    }
}
