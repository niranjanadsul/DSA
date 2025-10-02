package Stack_Queue.MonotonicStack_10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class NextsmallerElement_3 {
    static ArrayList<Integer> nextSmallerEle(int[] nums2) {
        // code here
        Stack<Integer> monoDecStack = new Stack<>();
        int[] ng= new int[nums2.length];

        for(int i = 0;i<nums2.length;i++){
            int currValue = nums2[i];
            if(monoDecStack.isEmpty()){
                monoDecStack.push(i);
            }else{
                while(!monoDecStack.isEmpty() && nums2[monoDecStack.peek()]>currValue){
                    ng[monoDecStack.pop()]= currValue;
                }
                monoDecStack.push(i);
            }
        }
        while(!monoDecStack.isEmpty()){
            ng[monoDecStack.pop()]= -1;
        }
        ArrayList<Integer> arr=new ArrayList<>();
        for(int i:ng)
            arr.add(i);
        return arr;
    }
}
