package Stack_Queue;

public class ImplementQueueUsingArray_2 {
    //https://www.geeksforgeeks.org/problems/implement-queue-using-array/1
    /*Implement a Queue using an Array. Queries in the Queue are of the following type:
        (i) 1 x   (a query of this type means  pushing 'x' into the queue)
        (ii) 2     (a query of this type means to pop an element from the queue and
        print the popped element. If the queue is empty then return -1)
        We just have to implement the functions push and pop and the driver code will handle the output.

        Examples:

        Input: Queries = 1 2 1 3 2 1 4 2
        Output: 2 3
        Explanation: For query 1 2 the queue will be {2} 1 3 the queue will be {2 3} 2   poped element
        will be 2 the queue will be {3} 1 4 the queue will be {3 4} 2 popped element will be 3*/

    int front, rear;
    int arr[] = new int[100005];

    ImplementQueueUsingArray_2() {
        front = 0;
        rear = 0;
    }

    // Function to push an element x in a queue.
    void push(int x) {
        arr[rear++]=x;
    }

    // Function to pop an element from queue and return that element.
    int pop() {
        if(front==rear)
            return -1;
        return arr[front++];

    }

    public static void main(String[] args) {
        ImplementQueueUsingArray_2 implementQueueUsingArray2=new ImplementQueueUsingArray_2();
        implementQueueUsingArray2.push(73);
        implementQueueUsingArray2.pop();
        implementQueueUsingArray2.pop();
        implementQueueUsingArray2.pop();
    }
}
