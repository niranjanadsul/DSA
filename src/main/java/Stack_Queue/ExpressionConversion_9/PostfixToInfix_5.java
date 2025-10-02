package Stack_Queue.ExpressionConversion_9;

import java.util.Stack;

public class PostfixToInfix_5 {
    //https://www.geeksforgeeks.org/problems/postfix-to-infix-conversion/1
    /*You are given a string that represents the postfix form of a valid mathematical expression. Convert it to its infix form.

    Example:

    Input:
    ab*c+
    Output:
    ((a*b)+c)
    Explanation:
    The above output is its valid infix form.*/
    static String postToInfix(String exp) {
        Stack<String> stack= new Stack<>();
        for(char c:exp.toCharArray()){
            if(('a'<=c && c<='z') || ('A'<=c && c<='Z') || ('0'<=c && c<='9'))
                stack.push(""+c);
            else {
                String op2=stack.pop();
                String op1=stack.pop();
                stack.push("("+op1+c+op2+")");
            }
        }
        return stack.peek();
    }
}
