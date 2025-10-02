package Stack_Queue.MonotonicStack_10;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveKDigits_9 {
    //https://leetcode.com/problems/remove-k-digits/description/
    /*Given string num representing a non-negative integer num, and an integer k,
    return the smallest possible integer after removing k digits from num.

    Example 1:

    Input: num = "1432219", k = 3
    Output: "1219"
    Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
    Example 2:

    Input: num = "10200", k = 1
    Output: "200"
    Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
    Example 3:

    Input: num = "10", k = 2
    Output: "0"
    Explanation: Remove all the digits from the number and it is left with nothing which is 0.*/

    /*Solution
    We want the resulting number to be as small as possible.
    When we encounter a digit d, if the previous digit is bigger than d,
    then removing the previous digit helps make the number smaller.

    So we pop from stack while k > 0 and top > d.
    Push the current digit.

    Finally:
    If k > 0, remove remaining digits from the end.
    Strip leading zeros.
    If result is empty, return "0"

    Input: num = "1432219", k = 3
    Start with stack = [].
    Add '1' → [1]
    Add '4' → [1,4]
    Next '3' < '4' → pop '4', k=2 → [1], then push '3' → [1,3]
    Next '2' < '3' → pop '3', k=1 → [1], push '2' → [1,2]
    Next '2' → push → [1,2,2]
    Next '1' < '2' → pop '2', k=0 → [1,2], then push '1' → [1,2,1]
    Push '9' → [1,2,1,9]
    Result = "1219"

    Time: O(n)
    */

    public String removeKdigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<>();
        //can be used as stack and queue for removing leading zero

        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && stack.peekLast() > c) {
                stack.pollLast();
                k--;
            }
            stack.addLast(c);
        }

        // If k > 0, remove from end
        while (k > 0 && !stack.isEmpty()) {
            stack.pollLast();
            k--;
        }

        // Build result
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        for (char c : stack) {
            if (leadingZero && c == '0') continue;
            leadingZero = false;
            sb.append(c);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}
