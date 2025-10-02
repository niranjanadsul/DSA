package MorganStanleyTest;

import java.util.Arrays;

public class PilesOfBoxes_1 {
    public int countSteps(int n, int[] piles){
        Arrays.sort(piles);
        int i=piles.length-1;
        int steps = 0;
        while(i>0){
            if(piles[i-1]<piles[i]){
                int count = 0;
                for(int j=i;j<piles.length;j++){
                    piles[j]=piles[i-1];
                    count++;
                }
                steps+=count;
            }
            i--;
        }
        return steps;
    }

    public static void main(String[] args) {
        PilesOfBoxes_1 piles= new PilesOfBoxes_1();
        int count = piles.countSteps(5, new int[]{4,5,5,2,4});
        System.out.println(count);//6
        count = piles.countSteps(3, new int[]{5,2,1});
        System.out.println(count);//3
    }
}
