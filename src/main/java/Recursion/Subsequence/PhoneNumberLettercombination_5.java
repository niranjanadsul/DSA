package Recursion.Subsequence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PhoneNumberLettercombination_5 {
    //https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    /*Given a string containing digits from 2-9 inclusive, return all possible
    letter combinations that the number could represent. Return the answer in any order.

    A mapping of digits to letters (just like on the telephone buttons) is given below.
    Note that 1 does not map to any letters.

    Example 1:

    Input: digits = "23"
    Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
    Example 2:

    Input: digits = "2"
    Output: ["a","b","c"]*/
    HashMap<String, String> digi;
    List<String> res;
    public List<String> letterCombinations(String digits) {
        this.digi = new HashMap<>();
        this.res = new ArrayList<>();
        digi.put("2", "abc");
        digi.put("3", "def");
        digi.put("4", "ghi");
        digi.put("5", "jkl");
        digi.put("6", "mno");
        digi.put("7", "pqrs");
        digi.put("8", "tuv");
        digi.put("9", "wxyz");

        if(digits.length()>0)
            permutor(0, digits, "");
        return res;
    }

    public void permutor(int i, String digits, String cur){
        if(cur.length() == digits.length()){
            this.res.add(cur);
            return;
        }
        String curDig = charToString(i, digits);
        String curDigString = digi.get(curDig);
        for(int j=0; j< curDigString.length(); j++){
            String alpha = charToString(j, curDigString);
            permutor(i+1, digits, cur+alpha);
        }

    }

    public String charToString(int i, String s){
        char [] c = {s.charAt(i)};
        return new String(c);
    }

    public static void main(String[] args) {
        PhoneNumberLettercombination_5 phoneNumberLettercombination5=new PhoneNumberLettercombination_5();
        System.out.println(phoneNumberLettercombination5.letterCombinations("23"));//9

    }
}
