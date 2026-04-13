package Recursion;

import java.util.List;
import java.util.Stack;

public class SortStack_3 {
    //https://www.geeksforgeeks.org/problems/sort-a-stack/1
    /*
    * Given a stack of integers st[]. Sort the stack in ascending order
    *  (smallest element at the bottom and largest at the top).

    Examples:

    Input: st[] = [1, 2, 3]
    Output: [3, 2, 1]
    Explanation: The stack is already sorted in ascending order.

    Input: st[] = [41, 3, 32, 2, 11]
    Output: [41, 32, 11, 3, 2]
    Explanation: After sorting, the smallest element (2) is at the bottom and the largest
    * element (41) is at the top.*/
    //pop each element into the function stack and then position the elements
    public Stack<Integer> sort(Stack<Integer> s) {
        if(s.isEmpty())
            return s;

        int temp = s.pop();
        sort(s);
        pushToBottom(s,temp);
        return s;
    }

    public void pushToBottom(Stack<Integer> s, int temp){
        if(s.isEmpty() || s.peek()<temp){
            s.push(temp);
            return;
        }
        int t=s.pop();
        pushToBottom(s,temp);
        s.push(t);
    }

    public static void main(String[] args) {
        SortStack_3 sortStack3 = new SortStack_3();
        Stack<Integer> s = new Stack<>();
        s.addAll(List.of(11,2,32,3, 41));
        sortStack3.sort(s);
        System.out.println("sorted");
    }
}
