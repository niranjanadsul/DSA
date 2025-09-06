package Stack_Queue.MonotonicStack_10;

import java.util.Arrays;
import java.util.Collection;
import java.util.Stack;

public class NextGreaterElement2_2 {
    /*Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
    return the next greater number for every element in nums.
    The next greater number of a number x is the first greater number to its traversing-order
     next in the array, which means you could search circularly to find its next greater number.
      If it doesn't exist, return -1 for this number.

    Example 1:
    Input: nums = [1,2,1]
    Output: [2,-1,2]
    Explanation: The first 1's next greater number is 2;
    The number 2 can't find next greater number.
    The second 1's next greater number needs to search circularly, which is also 2.
    Example 2:
    Input: nums = [1,2,3,4,3]
    Output: [2,3,4,-1,4]*/
    public int[] nextGreaterElements(int[] nums2) {
        Stack<Integer> monoIncStack = new Stack<>();
        int[] ng= new int[nums2.length];

        for(int i = 0;i<nums2.length*2;i++){
            int currValue = nums2[i % nums2.length];
            if(monoIncStack.isEmpty()){
                monoIncStack.push(i);
            }else{
                while(!monoIncStack.isEmpty() && nums2[monoIncStack.peek()%nums2.length]<currValue){
                    int index= monoIncStack.pop();
                    if(index<nums2.length)
                        ng[index]= currValue;
                }
                monoIncStack.push(i);
            }
        }
        while(!monoIncStack.isEmpty()){
            int index= monoIncStack.pop();
            if(index<nums2.length)
                ng[index]= -1;
        }
        return ng;
    }

    public static void main(String[] args) {
        NextGreaterElement2_2 nextGreaterElement22=new NextGreaterElement2_2();
        nextGreaterElement22.nextGreaterElements(new int[]{1,2,3,4,3});
    }
}
