package GreedyAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NMeetingInRoom_4 {
    //https://www.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1
    /*You are given timings of n meetings in the form of (start[i], end[i])
    where start[i] is the start time of meeting i and end[i] is the finish time of meeting i.
     Return the maximum number of meetings that can be accommodated in a single meeting room,
      when only one meeting can be held in the meeting room at a particular time.

    Note: The start time of one chosen meeting can't be equal to the end time of the other
    chosen meeting.

    Examples :

    Input: start[] = [1, 3, 0, 5, 8, 5], end[] =  [2, 4, 6, 7, 9, 9]
    Output: 4
    Explanation: Maximum four meetings can be held with given start and end timings.
    The meetings are - (1, 2), (3, 4), (5,7) and (8,9)
    Input: start[] = [10, 12, 20], end[] = [20, 25, 30]
    Output: 1
    Explanation: Only one meetings can be held with given start and end timings.
    Input: start[] = [1, 2], end[] = [100, 99]
    Output: 1*/
    public int maxMeetings(int start[], int end[]) {
        List<int[]> schedules=new ArrayList<>();
        for(int i=0;i<start.length;i++){
            schedules.add(new int[]{start[i],end[i]});
        }
        schedules.sort(Comparator.comparingInt(a -> a[1]));
        int count=0;
        int lastEnd=-1;  //0 ≤ start[i] < end[i] ≤ 10^6
        for(int[] schedule:schedules){
            if(lastEnd<schedule[0]){
                count++;
                lastEnd=schedule[1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NMeetingInRoom_4 nMeetingInRoom4=new NMeetingInRoom_4();
        System.out.println(nMeetingInRoom4.maxMeetings(new int[]{1, 3, 0, 5, 8, 5},
                new int[]{2, 4, 6, 7, 9, 9}));//4
        System.out.println(nMeetingInRoom4.maxMeetings(new int[]{10, 12, 20},
                new int[]{20, 25, 30}));//1
    }
}
