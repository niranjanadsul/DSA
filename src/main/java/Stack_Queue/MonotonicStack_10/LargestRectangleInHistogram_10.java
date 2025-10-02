package Stack_Queue.MonotonicStack_10;

import java.util.Stack;

public class LargestRectangleInHistogram_10 {
    //https://leetcode.com/problems/largest-rectangle-in-histogram/
    /*Given an array of integers heights representing the histogram's bar height
    where the width of each bar is 1, return the area of the largest rectangle in the histogram.
    Example 1:
    Input: heights = [2,1,5,6,2,3]
    Output: 10
    Explanation: The above is a histogram where width of each bar is 1.
    The largest rectangle is shown in the red area, which has an area = 10 units.*/
    class Pair{
        int startIndex;
        int height;

        Pair(int s, int h){
            startIndex=s;
            height=h;
        }
    }
    public int largestRectangleArea(int[] heights) {
        Stack<Pair> st = new Stack<>();
        int maxArea = 0;
        for(int i = 0; i<heights.length;i++){
            int start=i;
            while(!st.isEmpty() && st.peek().height>heights[i]){
                int currArea = (i-st.peek().startIndex) * st.peek().height;
                maxArea=Math.max(maxArea,currArea);
                start = st.pop().startIndex;
            }
            st.push(new Pair(start,heights[i]));
        }

        while(!st.isEmpty()){
            Pair p = st.pop();
            int currArea = (heights.length-p.startIndex) * p.height;
            maxArea=Math.max(maxArea,currArea);
        }
        return maxArea;
    }
}
