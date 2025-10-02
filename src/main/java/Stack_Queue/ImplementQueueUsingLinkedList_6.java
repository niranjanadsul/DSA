package Stack_Queue;

public class ImplementQueueUsingLinkedList_6 {
    //https://www.geeksforgeeks.org/problems/implement-queue-using-linked-list/1
    /*Implement a Queue using Linked List.
    A Query Q is of 2 Types
    (i) 1 x   (a query of this type means  pushing 'x' into the queue)
    (ii) 2     (a query of this type means to pop an element from the queue and print the poped element)

    Examples:

    Input: Q = 5, Queries = [[1, 2], [1, 3], [2], [1, 4], [2]]
    Output: 2 3
    Explanation:
    [1,2] queue will be 2
    [1,3] queue will be 2,3
    [2] poped element will be 2 the queue will be 3
    [1, 4] queue will be 3, 4
    [2] poped element will be 3
    Input: Q = 4, Queries = [[1, 2], [2], [2], [1, 3]]
    Output: 2 -1
    Explanation:
    [1, 2] queue will be 2
    [2]  poped element will be 2 then
        the queue will be empty.
    [2]  the queue is empty and hence -1
    [1, 3] the queue will be 3*/
    class QueueNode
    {
        int data;
        QueueNode next;
        QueueNode(int a)
        {
            data = a;
            next = null;
        }
    }

    QueueNode front, rear;

    // Function to push an element into the queue.
    void push(int a) {
        if(rear==null) {
            rear = new QueueNode(a);
            front=rear;
            return;
        }
        rear.next=new QueueNode(a);
        rear=rear.next;
    }

    // Function to pop front element from the queue
    int pop() {
        if(front==null)
            return -1;
        int x= front.data;
        front=front.next;
        if(front==null)//if queue becomes empty then rear needs to be pointed to front
            rear=front;
        return x;
    }

    public static void main(String[] args) {
        ImplementQueueUsingLinkedList_6 implementQueueUsingLinkedList6=new ImplementQueueUsingLinkedList_6();
        implementQueueUsingLinkedList6.push(78);
        System.out.println(implementQueueUsingLinkedList6.pop());
        System.out.println(implementQueueUsingLinkedList6.pop());
        System.out.println(implementQueueUsingLinkedList6.pop());
        implementQueueUsingLinkedList6.push(93);
        System.out.println(implementQueueUsingLinkedList6.pop());
    }
}
