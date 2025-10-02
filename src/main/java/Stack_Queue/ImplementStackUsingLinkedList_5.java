package Stack_Queue;

public class ImplementStackUsingLinkedList_5 {
    //https://www.geeksforgeeks.org/problems/implement-stack-using-linked-list/1
    /*Let's give it a try! You have a linked list and must implement the functionalities push and
    pop of stack using this given linked list. Your task is to use the class as shown in the comments in
    the code editor and complete the functions push() and pop() to implement a stack.
    The push() method takes one argument, an integer 'x' to be pushed into the stack and pop() which
    returns an integer present at the top and popped out from the stack. If the stack is empty then
     return -1 from the pop() method.
    Note: The input is given in the form of queries. Since there are two operations push() and pop(),
    there is two types of queries as described below:
    (i) 1   (a query of this type takes x as another parameter and pushes it into the stack)
    (ii) 2  (a query of this type means to pop an element from the stack and return the popped element)
    Input is separated by space and as described above.

    Examples :

    Input: [[1,2], [1,3], [2], [1,4], [2]]
    Output: [3, 4]
    Explanation:
    push(2)  : the stack will be {2}
    push(3)  : the stack will be {2 3}
    pop()    : poped element will be 3,the stack will be {2}
    push(4)  : the stack will be {2 4}
    pop()    : poped element will be 4*/

     class StackNode {
         int data;
         StackNode next;
         StackNode(int a) {
             data = a;
             next = null;
         }
     }

    StackNode top;

    // Function to push an integer into the stack.
    void push(int a) {
        if(top==null)
            top=new StackNode(a);
        //add node at front
        StackNode s=new StackNode(a);
        s.next=top;
        top=s;
    }

    // Function to remove an item from top of the stack.
    int pop() {
        if(top==null)
            return -1;
        StackNode s=top;
        top=top.next;
        return s.data;
    }
}
