package AmazonTest;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubStringPointCalculate {
    public static List<String> ls = new ArrayList<>();

    public static void subString(String s, int index, String curr,String prev){
        if(index >= s.length()){
            if(curr.length()>1){
                ls.add(curr);
            }
            return;
        }
        String c = ""+s.charAt(index);
        boolean take = true;

        if(!prev.isEmpty()){
            int newMaxDiff =  prev.charAt(0)-s.charAt(index);
            if(newMaxDiff <0)
                newMaxDiff*=-1;
            if(newMaxDiff!=1)
                take=false;
        }
        if(take)
            subString(s,index+1,curr+c,c);
        subString(s,index+1,curr,prev);
    }

    public static void main(String[] args) {
        String s = "abcb";//11
        String a ="aabacfgh";//16
        String c="abez";//5
        String d="abaz";//7
        String str= a;
        subString(str,0,"","");
        System.out.println(ls.size()+str.length());
        ls=new ArrayList<>();
    }
}
