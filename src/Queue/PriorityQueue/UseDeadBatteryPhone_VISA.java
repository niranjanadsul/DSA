package Queue.PriorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class UseDeadBatteryPhone_VISA {
    public int useFullBattery(int[] capapcity, int[] recharge, int t){
        // Comparator for min-heap based on the first element of the tuple
        //tuple 0 = battery availability time   & 1 = battery index
        Comparator<int[]> tupleComparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0])
                    return Integer.compare(o1[1],o2[1]);
                return Integer.compare(o1[0],o2[0]);
            }
        };

        // Create a PriorityQueue with the custom comparator
        PriorityQueue<int[]> pq = new PriorityQueue<>(tupleComparator);
        for(int i=capapcity.length-1; i>=0;i--){
            pq.add(new int[]{0,i});
            //initially all battery are available
        }
        int time=0;
        int count =0;
        while(time<t){
            if(pq.peek()[0]>time){
                return -1;
                //this means there is no battery available and all are recharging
            }
            //battery is available
            int batteryIndex=pq.poll()[1];
            time = time+capapcity[batteryIndex];
            count++;
            //time range extended by the capacity of the battery
            //now put the battery to recharge
            pq.add(new int[]{time+recharge[batteryIndex],batteryIndex});
        }
        return time>t?count-1:count;
    }

    public static void main(String[] args) {
        UseDeadBatteryPhone_VISA useDeadBatteryPhoneVisa=new UseDeadBatteryPhone_VISA();
        System.out.println(useDeadBatteryPhoneVisa.useFullBattery(new int[]{2,5,6},new int[]{12,1,4},16));//3
    }

}
