package SlidingWindow.TwoPointer;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        String ans = "";

        Map<Character, Integer> charMap = new HashMap<>();
        for(char c:t.toCharArray()){
            if(charMap.containsKey(c)){
                charMap.put(c,charMap.get(c)+1);
            }else{
                charMap.put(c,1);
            }
        }

        int count = 0,length=Integer.MAX_VALUE;
        int i =0,j=0, startIndex=-1;
        while(j<s.length()){
            char c=s.charAt(j);
            if(charMap.containsKey(c)){
                int tempCount = charMap.get(c);
                if(tempCount>0){
                    charMap.put(c,tempCount-1);
                    count++;
                }else{
                    charMap.put(c,tempCount-1);
                }
            }
            while(count==t.length()){
                if(length>(j-i+1)) {
                    startIndex = i;
                    length=j-i+1;
                }
                if(charMap.containsKey(s.charAt(i))){
                    int tempCount = charMap.get(s.charAt(i));
                    if(tempCount>=0){
                        count--;
                    }
                    charMap.put(s.charAt(i),tempCount+1);
                }
                i++;
            }
            j++;

        }
        if(startIndex>=0 && startIndex+length<=s.length())
            ans = s.substring(startIndex,startIndex+length);
        return ans;
    }

    public static void main(String[] arg){
        String s = "a", t = "a";
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
        System.out.println(minimumWindowSubstring.minWindow(s,t));
    }
}
