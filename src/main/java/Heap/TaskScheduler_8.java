package Heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TaskScheduler_8 {
        //https://leetcode.com/problems/task-scheduler/description/
    /*You are given an array of CPU tasks, each labeled with a letter from A to Z,
    and a number n. Each CPU interval can be idle or allow the completion of one task.
     Tasks can be completed in any order, but there's a constraint:
     there has to be a gap of at least n intervals between two tasks with the same label.
     Return the minimum number of CPU intervals required to complete all tasks.

    Example 1:
    Input: tasks = ["A","A","A","B","B","B"], n = 2
    Output: 8
    Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
    After completing task A, you must wait two intervals before doing A again.
    The same applies to task B. In the 3rd interval, neither A nor B can be done,
     so you idle. By the 4th interval, you can do A again as 2 intervals have passed.

    Example 2:
    Input: tasks = ["A","C","A","B","D","B"], n = 1
    Output: 6
    Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.
    With a cooling interval of 1, you can repeat a task after just one other task.

    Example 3:
    Input: tasks = ["A","A","A", "B","B","B"], n = 3
    Output: 10
    Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle ->
    idle -> A -> B.
    There are only two types of tasks, A and B, which need to be separated by 3 intervals.
     This leads to idling twice between repetitions of these tasks.*/
    static class Pair{
        public int frequency;
        public int availableTime;
        public Pair(int y,int z){
            frequency=y;
            availableTime=z;
        }
    }
    public int leastInterval(char[] tasks, int n) {
        PriorityQueue<Integer> availablePQ=new PriorityQueue<>((a,b)->b-a);
        PriorityQueue<Pair> pq=new PriorityQueue<>(Comparator.comparingInt(a -> a.availableTime));
        Map<Character,Integer> mp = new HashMap<>();
        for(char c:tasks){
            mp.compute(c,(k,v)->v==null?1:v+1);
        }
        for(Map.Entry<Character,Integer> entry:mp.entrySet())
            availablePQ.add(entry.getValue());
        int currTime=0;
        int task=tasks.length;
        while(task>0){
            currTime++;
            while(!pq.isEmpty() && pq.peek().availableTime==currTime){
                availablePQ.add(pq.poll().frequency);
            }
            if(availablePQ.isEmpty())
                continue;
            int currFreq=availablePQ.poll();
            currFreq--;
            if(currFreq>0)
                pq.add(new Pair(currFreq,currTime+1+n));
            task--;
        }
        return currTime;
    }

    public static void main(String[] args) {
        TaskScheduler_8 taskScheduler8=new TaskScheduler_8();
        System.out.println(taskScheduler8.leastInterval(new char[]{'B','C','D','A','A','A','A','G'}, 1));//8
        System.out.println(taskScheduler8.leastInterval(new char[]{'A','A','A','B','B','B'}, 2));//8
        System.out.println(taskScheduler8.leastInterval(new char[]{'A','C','A','B','D','B'}, 1));//6
        System.out.println(taskScheduler8.leastInterval(new char[]{'A','A','A','B','B','B'}, 3));//10
    }
}
