package OnlineTests;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ProcessScheduling {

    public static void main(String[] str) {
        System.out.println(getTime());
    }

    public static int getTime(){
        int n = 3;
        int m = 3;
        int[] processsSize = new int[]{2,5,7};
        Arrays.sort(processsSize);
        int[] capacity=new int[]{6,2,4};
        Arrays.sort(capacity);
        boolean[] available = new boolean[m];
        Arrays.fill(available, true);

        boolean[] complete = new boolean[n];
        Arrays.fill(complete, false);

        int time = 0;
        int completeProcess = 0;
        while(completeProcess < n){
            time++;
            for(int i =0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(available[j] && !complete[i] && processsSize[i]<=capacity[j]){
                        available[j]=false;
                        complete[i]=true;
                        completeProcess++;
                    }
                }
            }
            int availableCount=0;
            for(boolean a:available){
                if(a)
                    availableCount++;
            }
            if(availableCount==m && completeProcess!=n) {
                return -1;
            }
            Arrays.fill(available,true);
            if(completeProcess<n)
                time++;
        }
        return time;
    }

}
