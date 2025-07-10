package DynamicProgramming.LIS_6;

import java.util.ArrayList;
import java.util.Arrays;

public class PrintLIS_4 {
    public ArrayList<Integer> getLIS(int nums[]) {
        ArrayList<Integer> dp = new ArrayList<>();
        ArrayList<Integer> actualLIS = new ArrayList<>();
        int[] insertedAtLen = new int[nums.length];
        for(int i=0;i<nums.length;i++){ //TC n
            int num = nums[i];
            if(dp.isEmpty() || dp.getLast()<num) {
                dp.addLast(num);
                insertedAtLen[i]=dp.size();
            }else {
                int lb=floor(dp,num); // TC log n
                if(lb!=-1 && dp.get(lb)==num){
                    insertedAtLen[i]=lb+1;
                    continue;
                }
                insertedAtLen[i]=lb+2;
                dp.remove(lb+1);
                dp.add(lb+1,num);
            }
            System.out.println("len "+i+1+":"+dp);
        }
        System.out.println(Arrays.toString(insertedAtLen));
        int len = dp.size();
        for(int i =insertedAtLen.length-1;i>=0;i--){
            if(insertedAtLen[i]==len) {
                actualLIS.addFirst(nums[i]);
                len--;
            }
        }
        return actualLIS;
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
        PrintLIS_4 printLIS3=new PrintLIS_4();
        System.out.println(printLIS3.getLIS(new int[]{1,5,7,10,9,6,8,9,2,3}));
    }
}
