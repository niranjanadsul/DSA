package DynamicProgramming.LIS_6;

import java.util.ArrayList;

public class LIS_BinarySearch_3 {
    //TC = O(n*logn)
    //operational output
    /*len 1:[4]
    len 1:[4, 10]
    len 1:[3, 10]
    len 1:[3, 8]
    len 1:[3, 8, 9]
            3
    len 1:[7]
    len 1:[7]
    len 1:[7]
    len 1:[7]
    len 1:[7]
    len 1:[7]
    len 1:[7]
            1*/
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> dp = new ArrayList<>();
        int i=1;
        for(int num:nums){ //TC n
            if(dp.isEmpty() || dp.getLast()<num)
                dp.addLast(num);
            else {
                int lb=floor(dp,num); // TC log n
                if(lb!=-1 && dp.get(lb)==num)
                    continue;
                dp.remove(lb+1);
                dp.add(lb+1,num);
            }
            System.out.println("len "+i+":"+dp);
        }
        return dp.size();
    }

    public int floor(ArrayList<Integer> num, int target){
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
        LIS_BinarySearch_3 lisBinarySearch3 = new LIS_BinarySearch_3();
        System.out.println(lisBinarySearch3.lengthOfLIS(new int[]{4,10,4,3,8,9}));//3
        System.out.println(lisBinarySearch3.lengthOfLIS(new int[]{7,7,7,7,7,7,7}));//1
    }
}
