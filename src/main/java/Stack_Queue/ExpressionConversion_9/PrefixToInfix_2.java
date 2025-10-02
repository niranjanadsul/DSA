package Stack_Queue.ExpressionConversion_9;

import java.util.Stack;

public class PrefixToInfix_2 {
    //https://www.geeksforgeeks.org/problems/prefix-to-infix-conversion/1
    /*You are given a string S of size N that represents
    the prefix form of a valid mathematical expression.
    The string S contains only lowercase and uppercase alphabets as operands and
    the operators are +, -, *, /, %, and ^.Convert it to its infix form.

    Example 1:

    Input:
    *-A/BC-/AKL
    Output:
    ((A-(B/C))*((A/K)-L))
    Explanation:
    The above output is its valid infix form.*/
    static String preToInfix(String pre_exp) {
        Stack<String> stack=new Stack<>();
        for(int i=pre_exp.length()-1;i>=0;i--){
            char c=pre_exp.charAt(i);
            if(('a'<=c && c<='z') || ('A'<=c && c<='Z') || ('0'<=c && c<='9'))
                stack.push(""+c);
            else {
                stack.push("("+stack.pop()+c+stack.pop()+")");
            }
        }
        return stack.peek();
    }

    public static void main(String[] args) {
        System.out.println(PrefixToInfix_2.preToInfix("*-A/BC-/AKL"));//((A-(B/C))*((A/K)-L))
    }
}
