package BinarySearch;

public class CeilAndFloorInUnsortedArray {
    public static int[] ceilAndFloor(int[] num, int target){
        int start=0,end=num.length-1;
        int ceil=Integer.MAX_VALUE;
        int floor=-1;
        for(int i:num){
            if(i==target){
                return new int[]{i,i};
            }
            if(i<target)
                floor=Math.max(floor,i);
            if(i>target)
                ceil=Math.min(ceil,i);
        }
        if (ceil==Integer.MAX_VALUE)
            ceil=-1;
        return new int[]{floor,ceil};
    }

    public static void main(String[] args){
        int [] ceilFloor;
        ceilFloor= ceilAndFloor(new int[]{5, 6, 8, 9, 6, 5, 5, 6},7);
        System.out.println(ceilFloor[0]+" "+ceilFloor[1]);
        ceilFloor= ceilAndFloor(new int[]{5, 6, 8, 8, 6, 5, 5, 6},10);
        System.out.println(ceilFloor[0]+" "+ceilFloor[1]);
    }
}
