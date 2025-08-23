package Stack_Queue;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueue_3 {
    //https://leetcode.com/problems/implement-stack-using-queues/description/
    /*Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).

    Implement the MyStack class:

    void push(int x) Pushes element x to the top of the stack.
    int pop() Removes the element on the top of the stack and returns it.
    int top() Returns the element on the top of the stack.
    boolean empty() Returns true if the stack is empty, false otherwise.
    Notes:

    You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
    Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.


    Example 1:

    Input
    ["MyStack", "push", "push", "top", "pop", "empty"]
    [[], [1], [2], [], [], []]
    Output
    [null, null, null, 2, 2, false]

    Explanation
    MyStack myStack = new MyStack();
    myStack.push(1);
    myStack.push(2);
    myStack.top(); // return 2
    myStack.pop(); // return 2
    myStack.empty(); // return False*/

    //we implement the stack using a single queue
    //as queue is a FIFO we need to convert the queue into LIFO after every push operation
    //if queue contains n elements then we need to rotate the queue for n-1 times so the last element
    //appears at front position
    //rotate is basically remove element from front and add it to rear
    //rest operations are simple

    Queue<Integer> stack;
    public ImplementStackUsingQueue_3() {
        stack=new LinkedList<>();
    }

    //TC=O(n)
    //make the queue look like last in first out
    public void push(int x) {
        stack.add(x);
        int i=stack.size();
        while(i>1){
            stack.add(stack.remove());
            i--;
        }
    }

    public int pop() {
        if(stack.isEmpty())
            return -1;
        return stack.remove();
    }

    public int top() {
        if(stack.isEmpty())
            return -1;
        return stack.peek();
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}
