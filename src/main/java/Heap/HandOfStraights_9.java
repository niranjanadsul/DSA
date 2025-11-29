package Heap;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class HandOfStraights_9 {
    //https://leetcode.com/problems/hand-of-straights/
    /*Alice has some number of cards and she wants to rearrange the cards into groups so
     that each group is of size groupSize, and consists of groupSize consecutive cards.
    Given an integer array hand where hand[i] is the value written on the ith card and
    an integer groupSize, return true if she can rearrange the cards, or false otherwise.

    Example 1:

    Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
    Output: true
    Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
    Example 2:

    Input: hand = [1,2,3,4,5], groupSize = 4
    Output: false
    Explanation: Alice's hand can not be rearranged into groups of 4.
*/
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if(hand.length%groupSize!=0)
            return false;
        int groups = hand.length/groupSize;
        HashMap<Integer,Integer> mp=new HashMap<>();
        for(int n:hand){
            mp.compute(n,(k,v)->v==null?1:v+1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i:mp.keySet())
            pq.add(i);
        while(groups>0){
            int start=pq.peek();
            if(!mp.containsKey(start)) {
                pq.poll();
                continue;
            }
            mp.compute(start,(k,v)->v-1);
            if(mp.get(start)==0){
                pq.poll();
                mp.remove(start);
            }
            for(int i=1;i<groupSize;i++){
                if(mp.containsKey(start+i)){
                    mp.compute(start+i,(k,v)->v-1);
                    if(mp.get(start+i)==0){
                        mp.remove(start+i);
                    }
                }else
                    return false;
            }
            groups--;
        }
        return true;
    }

    public static void main(String[] args) {
        HandOfStraights_9 handOfStraights9=new HandOfStraights_9();
        System.out.println(
                handOfStraights9.isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8},3));//true
        System.out.println(
                handOfStraights9.isNStraightHand(new int[]{1,2,3,4,5},4));//false
    }
}
