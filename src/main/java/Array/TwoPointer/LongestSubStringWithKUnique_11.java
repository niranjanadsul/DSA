package Array.TwoPointer;

import java.util.HashMap;

public class LongestSubStringWithKUnique_11 {
    //https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1
    /*You are given a string s consisting only lowercase alphabets and an integer k.
    Your task is to find the length of the longest substring
    that contains exactly k distinct characters.
    Note : If no such substring exists, return -1.
    Examples:

    Input: s = "aabacbebebe", k = 3
    Output: 7
    Explanation: The longest substring with exactly 3 distinct characters is "cbebebe",
    which includes 'c', 'b', and 'e'.
    Input: s = "aaaa", k = 2
    Output: -1
    Explanation: There's no substring with 2 distinct characters.
    Input: s = "aabaaab", k = 2
    Output: 7
    Explanation: The entire string "aabaaab" has exactly 2 unique characters 'a' and 'b',
    making it the longest valid substring.*/
    public int longestKSubstr(String s, int k) {
        int i=0,j=0;
        int len = -1;
        HashMap<Character,Integer> mp=new HashMap<>();
        while(j<s.length()){
            char curr=s.charAt(j);
            mp.compute(curr,(key,v)->v==null?1:v+1);
            while(mp.size()>k){
                //window resize
                mp.compute(s.charAt(i),(key,v)->v-1);
                if(mp.get(s.charAt(i))==0)
                    mp.remove(s.charAt(i));
                i++;
            }
            if(mp.size()==k)
                len=Math.max(len,j-i+1);
            j++;
        }
        return len;
    }
}
