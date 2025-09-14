package Stack_Queue.MonotonicStack_10;

import java.util.Arrays;
import java.util.Stack;

public class MaximalRectangle_11 {
    //https://leetcode.com/problems/maximal-rectangle/
    /*Given a rows x cols binary matrix filled with 0's and 1's,
    find the largest rectangle containing only 1's and return its area.
    Example 1:
    Input: matrix = [["1","0","1","0","0"],
                       ["1","0","1","1","1"],
                    ["1","1","1","1","1"],
                    ["1","0","0","1","0"]]
    Output: 6
    Explanation: The maximal rectangle is shown in the above picture.
    Example 2:

    Input: matrix = [["0"]]
    Output: 0
    Example 3:

    Input: matrix = [["1"]]
    Output: 1*/

    //T.C = O(  rows *     (columns     +       columns)) = O(rows * columns)
    //      for each row build histogram and find the largest rectangle for that histogram
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

    public int maximalRectangle(char[][] matrix) {
        if(matrix.length==0)
            return 0;
        int maxRect=0;
        int[] histogram=new int[matrix[0].length];
        Arrays.fill(histogram,0);
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]=='1')
                    histogram[j]++;
                else
                    histogram[j]=0;
            }
            maxRect=Math.max(maxRect,largestRectangleArea(histogram));
        }
        return maxRect;

    }
}
