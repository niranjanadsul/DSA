package Recursion.Subsequence;

import java.util.ArrayList;
import java.util.List;

public class GenerateAllStrings_1 {
    public static List<String> generateBinaryStrings(int n) {
        // code here
        List<String> ls = new ArrayList<>();
        subsequence(n,"",ls);
        return ls;
    }

    public static void subsequence(int count,String s, List<String> str){
        if(count==0) {
            str.add(s);
            return;
        }
        if(s.isEmpty() || s.charAt(s.length()-1) == '0'){
            subsequence(count-1,s+"0",str);
            subsequence(count-1,s+"1",str);
        }else{
            subsequence(count-1,s+"0",str);
        }
    }

    public static void main(String[] args) {
        List<String> ls = generateBinaryStrings(3);
        System.out.println("completed");
    }
}
