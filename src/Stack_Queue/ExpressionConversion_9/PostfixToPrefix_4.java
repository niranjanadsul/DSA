package Stack_Queue.ExpressionConversion_9;

import java.util.Stack;

public class PostfixToPrefix_4 {
    //https://www.geeksforgeeks.org/problems/postfix-to-prefix-conversion/1
    /*You are given a string that represents the postfix form of a valid mathematical expression.
    Convert it to its prefix form.
    Example 1:
    Input:
    ABC/-AK/L-*
    Output:
    *-A/BC-/AKL
    Explanation:
    The above output is its valid prefix form.

    Example 2:
    Input:
    ab+
    Output:
    +ab
    Explanation:
    The above output is its valid prefix form.*/
    static String postToPre(String post_exp) {
        Stack<String> stack= new Stack<>();
        for(char c:post_exp.toCharArray()){
            if(('a'<=c && c<='z') || ('A'<=c && c<='Z') || ('0'<=c && c<='9'))
                stack.push(""+c);
            else {
                String op2=stack.pop();
                String op1=stack.pop();
                stack.push(c+op1+op2);
            }
        }
        return stack.peek();
    }
}
