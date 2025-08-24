package Stack_Queue;

import java.util.Stack;

public class MinStack_8 {
    //https://leetcode.com/problems/min-stack/
    /*Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
    Implement the MinStack class:
    MinStack() initializes the stack object.
    void push(int val) pushes the element val onto the stack.
    void pop() removes the element on the top of the stack.
    int top() gets the top element of the stack.
    int getMin() retrieves the minimum element in the stack.
    You must implement a solution with O(1) time complexity for each function.

    Example 1:

    Input
    ["MinStack","push","push","push","getMin","pop","top","getMin"]
    [[],[-2],[0],[-3],[],[],[],[]]

    Output
    [null,null,null,null,-3,null,0,-2]

    Explanation
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    minStack.getMin(); // return -3
    minStack.pop();
    minStack.top();    // return 0
    minStack.getMin(); // return -2*/

    //we will basically store the value and min upto that value int eh stack

    Stack<int[]> stack;
    public MinStack_8() {
        stack=new Stack<>();
    }

    public void push(int val) {
        if(stack.isEmpty())
            stack.push(new int[]{val,val});
        else{
            int min = Math.min(val,stack.peek()[1]);
            stack.push(new int[]{val,min});
        }

    }

    public void pop() {
        if(!stack.isEmpty())
            stack.pop();
    }

    public int top() {
        if(stack.isEmpty())
            return -1;
        return stack.peek()[0];
    }

    public int getMin() {
        if(stack.isEmpty())
            return -1;
        return stack.peek()[1];
    }
}
