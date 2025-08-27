package Stack_Queue.ExpressionConversion_9;

import java.util.Stack;

public class InfixToPostFixConversion_1 {
    //https://www.geeksforgeeks.org/problems/infix-to-postfix-1587115620/1
    /*Given an infix expression in the form of string s.
    Convert this infix expression to a postfix expression.

    Infix expression: The expression of the form a op b.
    When an operator is in between every pair of operands.
    Postfix expression: The expression of the form a b op.
    When an operator is followed for every pair of operands.
    Note: The order of precedence is: ^ greater than * equals to / greater than + equals to -.
    Ignore the right associativity of ^.

    Examples :
    Input: s = "a+b*(c^d-e)^(f+g*h)-i"
    Output: abcd^e-fgh*+^*+i-
    Explanation: After converting the infix expression into postfix expression,
    the resultant expression will be abcd^e-fgh*+^*+i-

    Input: s = "A*(B+C)/D"
    Output: ABC+*D/
    Explanation: After converting the infix expression into postfix expression,
    the resultant expression will be ABC+*D/

    Input: s = "(a+b)*(c+d)"
    Output: ab+cd+**/
    public static String infixToPostfix(String s) {
        StringBuilder post = new StringBuilder();
        Stack<Character> st = new Stack<>();
        for(char c:s.toCharArray()){
            if(('a'<=c && c<='z') || ('A'<=c && c<='Z') || ('0'<=c && c<='9'))
                post.append(c);
            else if(c=='(')
                st.push(c);
            else if(c==')'){
                while (!st.isEmpty() && st.peek()!='('){
                    post.append(st.pop());
                }
                st.pop();//pop '('
            } else{
                //operator encountered
                while (!st.isEmpty() && getPrecedence(st.peek())>=getPrecedence(c)){
                    post.append(st.pop());
                }
                st.push(c);
            }
        }
        while (!st.isEmpty())
            post.append(st.pop());
        return post.toString();
    }

    public static int getPrecedence(char c){
        if(c=='^')
            return 3;
        if(c=='*' || c=='/')
            return 2;
        if(c=='+' || c=='-')
            return 1;
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(InfixToPostFixConversion_1.infixToPostfix("a+b*(c^d-e)^(f+g*h)-i"));
        //abcd^e-fgh*+^*+i-
    }
}
