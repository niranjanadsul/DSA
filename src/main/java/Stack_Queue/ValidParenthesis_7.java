package Stack_Queue;

import java.util.Stack;

public class ValidParenthesis_7 {
    //https://leetcode.com/problems/valid-parentheses/description/
    /*Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
    determine if the input string is valid.
    An input string is valid if:
    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.
    Every close bracket has a corresponding open bracket of the same type.
    Example 1:

    Input: s = "()"
    Output: true

    Example 2:
    Input: s = "()[]{}"
    Output: true

    Example 3:
    Input: s = "(]"
    Output: false

    Example 4:
    Input: s = "([])"
    Output: true

    Example 5:
    Input: s = "([)]"
    Output: false*/
    public boolean isValid(String s) {
        Stack<Character> stack=new Stack<>();
        for(Character c:s.toCharArray()){
            if(c=='(' || c=='{' || c=='[')
                stack.push(c);
            else{
                if(stack.isEmpty())
                    return false;
                Character top = stack.pop();
                if((top!='(' && c==')') || (top!='{' &&c=='}') || (top!='[' && c==']'))
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParenthesis_7 validParenthesis7=new ValidParenthesis_7();
        System.out.println(validParenthesis7.isValid("()"));
        System.out.println(validParenthesis7.isValid("["));
    }
}
