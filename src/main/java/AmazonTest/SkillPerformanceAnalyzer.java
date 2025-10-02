package AmazonTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SkillPerformanceAnalyzer {
    public static List<Integer> analyze(){
        int numSkills=3;
        int timeWindow = 5;
        int[][] requestLogs = new int[][]{{1,3},{2,6},{1,5}};
        int[] queryTimes = new int[]{10,11};

        List<Integer> count = new ArrayList<>();
        for(int queryTime:queryTimes){
            int lower = queryTime-timeWindow;
            int upper = queryTime;
            HashSet<Integer> queryRequestIDs= new HashSet<>();
            HashSet<Integer> allReq = new HashSet<>();
            for(int i =1;i<=numSkills;i++){
                allReq.add(i);
            }
            for(int[] tuple:requestLogs){
                int reqID = tuple[0];
                int timeStamp = tuple[1];
                if(lower<=timeStamp && timeStamp<=upper){
                    queryRequestIDs.add(reqID);
                }
            }
            allReq.removeAll(queryRequestIDs);
            count.add(allReq.size());
        }
        return count;
    }

    public static void main(String[] args) {
        analyze();
    }
}
