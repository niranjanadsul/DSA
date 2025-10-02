package Stack_Queue.ExpressionConversion_9;

import java.util.Stack;

public class PrefixToPostfix_3 {
    //https://www.geeksforgeeks.org/problems/prefix-to-postfix-conversion/1
    /*You are given a string that represents the prefix form of a valid mathematical expression.
    Convert it to its postfix form.

    Example:

    Input:
    *-A/BC-/AKL
    Output:
    ABC/-AK/L-*
    Explanation:
    The above output is its valid postfix form.*/
    static String preToPost(String pre_exp) {
        Stack<String> stack=new Stack<>();
        for(int i=pre_exp.length()-1;i>=0;i--){
            char c=pre_exp.charAt(i);
            if(('a'<=c && c<='z') || ('A'<=c && c<='Z') || ('0'<=c && c<='9'))
                stack.push(""+c);
            else {
                stack.push(stack.pop()+stack.pop()+c);
            }
        }
        return stack.peek();
    }
}
