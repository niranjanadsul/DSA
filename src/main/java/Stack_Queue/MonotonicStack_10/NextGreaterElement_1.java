package Stack_Queue.MonotonicStack_10;

import java.util.HashMap;
import java.util.Stack;

public class NextGreaterElement_1 {
    //https://leetcode.com/problems/next-greater-element-i/description/
    /*The next greater element of some element x in an array is the first greater element
    that is to the right of x in the same array.
    You are given two distinct 0-indexed integer arrays nums1 and nums2,
    where nums1 is a subset of nums2.
    For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and
     determine the next greater element of nums2[j] in nums2. If there is no next greater element,
      then the answer for this query is -1.
    Return an array ans of length nums1.length such that ans[i] is the next greater element as
     described above.
    Example 1:

    Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
    Output: [-1,3,-1]
    Explanation: The next greater element for each value of nums1 is as follows:
    - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
    - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
    - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
    Example 2:

    Input: nums1 = [2,4], nums2 = [1,2,3,4]
    Output: [3,-1]
    Explanation: The next greater element for each value of nums1 is as follows:
    - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
    - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.*/
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> monoIncStack = new Stack<>();
        int[] ng= new int[nums2.length];

        for(int i = 0;i<nums2.length;i++){
            int currValue = nums2[i];
            if(monoIncStack.isEmpty()){
                monoIncStack.push(i);
            }else{
                while(!monoIncStack.isEmpty() && nums2[monoIncStack.peek()]<=currValue){
                    ng[monoIncStack.pop()]= currValue;
                }
                monoIncStack.push(i);
            }
        }
        while(!monoIncStack.isEmpty()){
            ng[monoIncStack.pop()]= -1;
        }

        int[] ngReturn= new int[nums1.length];

        HashMap<Integer,Integer> valueToidMap = new HashMap<>();
        for(int i = 0;i<nums2.length;i++){
            valueToidMap.put(nums2[i],i);
        }

        for(int i = 0;i<nums1.length;i++){
            ngReturn[i] = ng[valueToidMap.get(nums1[i])];
        }
        return ngReturn;
    }
}
