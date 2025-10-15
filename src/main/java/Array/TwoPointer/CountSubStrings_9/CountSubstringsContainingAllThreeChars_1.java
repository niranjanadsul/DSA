package Array.TwoPointer.CountSubStrings_9;

import java.util.HashMap;

public class CountSubstringsContainingAllThreeChars_1 {
    //https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/
    /*Given a string s consisting only of characters a, b and c.
    Return the number of substrings containing at least one occurrence
    of all these characters a, b and c.
    Example 1:

    Input: s = "abcabc"
    Output: 10
    Explanation: The substrings containing at least one occurrence of the characters a, b and c
     are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc"
     and "abc" (again).
    Example 2:

    Input: s = "aaacb"
    Output: 3
    Explanation: The substrings containing at least one occurrence of the characters a, b and c
    are "aaacb", "aacb" and "acb".
    Example 3:

    Input: s = "abc"
    Output: 1*/
    public int numberOfSubstrings(String s) {
        int i=0,j=0;
        int count = 0;
        HashMap<Character,Integer> alphabetMap=new HashMap<>();
        while(j<s.length()){
            char c=s.charAt(j);
            //window expansion logic
            //increase the count of the alphabets/characters scanned by the forward pointer
            alphabetMap.compute(c,
                    (k,v)->alphabetMap.getOrDefault(c,0)+1);

            //if during expansion/shrinkage we come across all the 3 characters
            //we are sure that even if we further increment the forward pointer
            // all chars will be definitely present
            //we increment the count by length - j as each character will generate new SUBSTRING
            while(alphabetMap.size()==3){
                count+=s.length()-j;
                //we shrink until all 3 chars are present in the current window from i to j
                char ci=s.charAt(i);
                alphabetMap.put(ci,alphabetMap.get(ci)-1);
                if(alphabetMap.get(ci)==0)
                    alphabetMap.remove(ci);
                i++;
            }
            j++;
        }
        return count;
    }
}
