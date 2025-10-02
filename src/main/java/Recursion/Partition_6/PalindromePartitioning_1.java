package Recursion.Partition_6;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning_1 {
    //https://leetcode.com/problems/palindrome-partitioning/description/
    public List<List<String>> partition(String s) {
        List<List<String>> allPal=new ArrayList<>();
        findPalindrome(0,1,s,new ArrayList<>(),allPal);
        return allPal;
    }

    //partitions start from 1 up to s.length()
    public void findPalindrome(int start, int partition,String s, List<String> ls,List<List<String>> allPalindromes){
        if(partition>s.length()){
            if(start>=s.length() && !ls.isEmpty())
                allPalindromes.add(new ArrayList<>(ls));
            return;
        }
        //take partition if palindrome
        if(isPalindrome(s.substring(start,partition))){
            ls.add(s.substring(start,partition));
            findPalindrome(partition,partition+1,s,ls,allPalindromes);
            ls.removeLast();
        }
        findPalindrome(start,partition+1,s,ls,allPalindromes);
    }

    public boolean isPalindrome(String s){
        int i=0,j=s.length()-1;
        while(i<j){
            if(s.charAt(i)!=s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning_1 palindromePartitioning1 = new PalindromePartitioning_1();
        System.out.println(palindromePartitioning1.partition("aab"));
    }
}
