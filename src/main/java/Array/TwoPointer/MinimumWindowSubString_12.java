package Array.TwoPointer;

import java.util.HashMap;

public class MinimumWindowSubString_12 {
    //https://leetcode.com/problems/minimum-window-substring/description/
    /*Given two strings s and t of lengths m and n respectively,
    return the minimum window substring of s such that every character in t
    (including duplicates) is included in the window. If there is no such substring,
    return the empty string "".
    The testcases will be generated such that the answer is unique.

    Example 1:

    Input: s = "ADOBECODEBANC", t = "ABC"
    Output: "BANC"
    Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from
    string t.
    Example 2:

    Input: s = "a", t = "a"
    Output: "a"
    Explanation: The entire string s is the minimum window.
    Example 3:

    Input: s = "a", t = "aa"
    Output: ""
    Explanation: Both 'a's from t must be included in the window.
    Since the largest window of s only has one 'a', return empty string.*/
    public String minWindow(String s, String t) {
        HashMap<Character,Integer> tmap= new HashMap<>();
        for(char c:t.toCharArray()){
            tmap.compute(c,(k,v)->v==null?1:v+1);
        }
        int need = tmap.size();
        HashMap<Character,Integer> smap= new HashMap<>();
        int i=0,j=0, have = 0,start=-1,end = -1,len=Integer.MAX_VALUE;
        String ans = "";
        while(j<s.length())
        {
            if(tmap.containsKey(s.charAt(j))){
                smap.compute(s.charAt(j),(k,v)->v==null?1:v+1);
                int needed = tmap.get(s.charAt(j));
                if(smap.get(s.charAt(j))==needed){
                    have++;
                }
            }
            while(have==need){
                if(j-i+1<len){
                    len=j-i+1;
                    start=i;
                    end=j;
                }
                if(tmap.containsKey(s.charAt(i))){
                    smap.compute(s.charAt(i),(k,v)->v-1);
                    int needed =tmap.get(s.charAt(i));
                    if(smap.get(s.charAt(i))<needed){
                        have--;
                    }
                }
                i++;
            }
            j++;
        }
        if(start!=-1 && end!=-1)
            ans = s.substring(start,end+1);
        return ans;
    }

    public static void main(String[] args) {
        MinimumWindowSubString_12 minimumWindowSubString12=new MinimumWindowSubString_12();
        System.out.println(minimumWindowSubString12.minWindow("ADOBECODEBANC","ABC"));//BANC
        System.out.println(minimumWindowSubString12.minWindow("a","aa"));//
        System.out.println(minimumWindowSubString12.minWindow("a","a"));//a
    }
}
