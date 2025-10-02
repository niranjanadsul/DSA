package NagaroTest;

import java.util.*;

public class MaximumCoordinate {
    public int maximumCoord(int totalChkPts, int initialCoins,int[] chkPts,int[] chkPtReward){
        List<Integer> chkPtsLs = new LinkedList<>();
        for(int i:chkPts){
            chkPtsLs.add(i);
        }
        List<Integer> chkPtRewardLs = new LinkedList<>();
        for(int i:chkPtReward){
            chkPtRewardLs.add(i);
        }
        int j = 0;
        for(j=0;j<Integer.MAX_VALUE;j++){
            if(initialCoins==0)
                break;
            if(chkPtsLs.contains(j)){
                int index = chkPtsLs.indexOf(j);
                initialCoins+=chkPtRewardLs.get(index);
            }
            initialCoins--;
        }
        return j;
    }

    public static void main(String[] args) {
        MaximumCoordinate maximumCoordinate = new MaximumCoordinate();
        int maxCo = maximumCoordinate.maximumCoord(2,3,new int[]{2,5},new int[]{1,10});//4
        System.out.println(maxCo);
        maxCo = maximumCoordinate.maximumCoord(3,1,new int[]{4,5,7},new int[]{10,4,2});//1
        System.out.println(maxCo);
    }
}
