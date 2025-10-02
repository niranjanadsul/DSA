package Stack_Queue;

public class ImplementStackUsingArray_1 {
    //https://www.geeksforgeeks.org/problems/implement-stack-using-array/1
    /*Write a program to implement a stack using an array oper[]. You need  to complete the push(int x)
        and pop() methods inside a class MyStack to simulate the standard stack operations:
        push(x): Pushes the integer x onto the stack.
        pop(): Removes and returns the topmost element of the stack. If the stack is empty, return -1.
        You will be given a list of space-separated queries consisting of two types:
        Type 1 : 1 x — Push x into the stack.
        Type 2 : 2  — Pop the top element from the stack and print it. If the stack is empty, print -1.
        Note: It is guaranteed that for Type 1, there will always be a value x.*/

    int[] stack;
    int top;
    public void push(int x) {
        if (this.stack==null) {
            this.stack=new int[Integer.MAX_VALUE];
            this.top = -1;
        }
        this.stack[++top]=x;
    }

    public int pop() {
        if(top==-1 || this.stack==null)
            return -1;
        return this.stack[this.top--];
    }
}
