package Recursion;

import java.util.List;
import java.util.Stack;

public class ReverseStack_4 {
    //https://www.geeksforgeeks.org/problems/reverse-a-stack/1
    public void reverse(Stack<Integer> s) {
        if(s.isEmpty())
            return;

        int temp = s.pop();
        reverse(s);
        pushToBottom(s,temp);
    }

    public void pushToBottom(Stack<Integer> s, int temp){
        if(s.isEmpty()){
            s.push(temp);
            return;
        }
        int t=s.pop();
        pushToBottom(s,temp);
        s.push(t);
    }

    public static void main(String[] args) {
        ReverseStack_4  reverseStack4 = new ReverseStack_4();
        Stack<Integer> s = new Stack<>();
        s.addAll(List.of(4,3,9,6));
        reverseStack4.reverse(s);
        System.out.println("sorted");
    }
}
