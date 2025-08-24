package Stack_Queue;

import java.util.Stack;

public class ImplementQueueUsingStack_4 {
    //https://leetcode.com/problems/implement-queue-using-stacks/
    /*Implement a first in first out (FIFO) queue using only two stacks.
    The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
    Implement the MyQueue class:
    void push(int x) Pushes element x to the back of the queue.
    int pop() Removes the element from the front of the queue and returns it.
    int peek() Returns the element at the front of the queue.
    boolean empty() Returns true if the queue is empty, false otherwise.
    Notes:
    You must use only standard operations of a stack, which means only push to top, peek/pop from top,
    size, and is empty operations are valid.
    Depending on your language, the stack may not be supported natively.
    You may simulate a stack using a list or deque (double-ended queue) as long as you use only a
    stack's standard operations.

    Example 1:
    Input
    ["MyQueue", "push", "push", "peek", "pop", "empty"]
    [[], [1], [2], [], [], []]
    Output
    [null, null, null, 1, 1, false]

    Explanation
    MyQueue myQueue = new MyQueue();
    myQueue.push(1); // queue is: [1]
    myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
    myQueue.peek(); // return 1
    myQueue.pop(); // return 1, queue is [2]
    myQueue.empty(); // return false*/

    //we will use single stack
    //push operation will be O(1)
    //but for pop or peek operation we will use recursion to pop each element in the stack until only 1
    //element is left. This will actually be the front of the queue as stack is LIFO and queue is FIFO
    //as the recursive calls return we will again push the popped element back to stack.
    //this will ensure that the front is removed of peeked

    private Stack<Integer> st;
    public ImplementQueueUsingStack_4() {
        this.st=new Stack<>();
    }

    public void push(int x) {
        this.st.push(x);
    }

    public int pop() {
        if(this.st.isEmpty())
            return -1;
        return getQueueFront(true);
    }

    public int peek() {
        if(this.st.isEmpty())
            return -1;
        return getQueueFront(false);
    }

    public boolean empty() {
        return this.st.isEmpty();
    }

    private int getQueueFront(boolean removeFront){
        if(this.st.size()==1){
            if(removeFront)
                return this.st.pop();
            else
                return this.st.peek();
        }
        int currentTop=this.st.pop();
        int actualFront=getQueueFront(removeFront);
        this.st.push(currentTop);
        return actualFront;
    }
}
