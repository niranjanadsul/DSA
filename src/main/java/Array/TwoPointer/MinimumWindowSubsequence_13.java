package Array.TwoPointer;

public class MinimumWindowSubsequence_13 {
    //https://www.geeksforgeeks.org/dsa/minimum-window-subsequence/
    /*Given two strings s1 and s2,
    find the smallest contiguous substring of s1 in which s2 appears as a subsequence.

    The characters of s2 must appear in the same order within the substring,
    but not necessarily consecutively.
    If multiple substrings of the same minimum length satisfy the condition,
    return the one that appears earliest in s1.
    If no such substring exists, return an empty string.
    Both s1 and s2 consist only of lowercase English letters.
    Examples:

    Input: s1 = "geeksforgeeks", s2 = "eksrg"
    Output: "eksforg"
    Explanation: "eksforg" satisfies all required conditions. s2 is its subsequence and
    it is smallest and leftmost among all possible valid substrings of s1.

    Input: s1 = "abcdebdde", s2 = "bde"
    Output: "bcde"
    Explanation: "bcde" and "bdde" are two substring of s1 where s2 occurs as subsequence
    but "bcde" occur first so we return that.

    Input: s1 = "ad", s2 = "b"
    Output: ""
    Explanation: There is no substring exists.*/

    /*Two-Pointer Forward Scan with Backtracking - O(n^2) Time and O(1) Space
    The idea is to iterate through s1 and,
    for each position where the first character of s2 matches,
    move forward with two pointers to find the complete subsequence s2.

    Once matched, backtrack from the end position to shrink the window to the
    smallest substring that still contains s2 as a subsequence.
    Track the minimum-length substring found and update it whenever a smaller one is
    discovered.
    If no match is found, return an empty string.*/
    String minWindow(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        String ans = "";
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            // find starting point where s1[i] matches s2[0]
            if (s1.charAt(i) == s2.charAt(0)) {
                int p1 = i, p2 = 0;

                // move forward until s2 is matched
                while (p1 < n && p2 < m) {
                    if (s1.charAt(p1) == s2.charAt(p2)) p2++;
                    p1++;
                }

                // if we matched all characters of s2
                if (p2 == m) {
                    // last matched index
                    int end = p1 - 1;
                    p2 = m - 1;

                    // backtrack to find minimal starting index
                    while (end >= i) {
                        if (s1.charAt(end) == s2.charAt(p2)) p2--;
                        if (p2 < 0) break;
                        end--;
                    }

                    int start = end;
                    int len = p1 - start;
                    if (len < minLen) {
                        minLen = len;
                        ans = s1.substring(start, start + len);
                    }
                }
            }
        }
        return ans;
    }
}
