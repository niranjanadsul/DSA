package Recursion.Subsequence;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis_2 {
    public List<String> generateParenthesis(int n) {
        List<String> ls=new ArrayList<>();
        generate(n,ls,0,0,"");
        return ls;
    }

    public void generate(int n,List<String> allParenthesis, int open, int close, String s){
        if(open==n && close==n){
            allParenthesis.add(s);
            return;
        }
        if(open==0){
            generate(n,allParenthesis,open+1,close,s+"(");
        }else if(open==n){
            generate(n,allParenthesis,open,close+1,s+")");
        }else if(open>=close){
            generate(n,allParenthesis,open+1,close,s+"(");
            generate(n,allParenthesis,open,close+1,s+")");
        }
    }

    public static void main(String[] args) {
        GenerateParenthesis_2 generateParenthesis2=new GenerateParenthesis_2();
        List<String> ls=generateParenthesis2.generateParenthesis(3);
        System.out.println();
    }
}
