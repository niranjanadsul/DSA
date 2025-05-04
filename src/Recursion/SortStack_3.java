package Recursion;

import java.util.List;
import java.util.Stack;

public class SortStack_3 {
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
