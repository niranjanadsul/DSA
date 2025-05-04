package OnlineTests;

import java.util.Arrays;

public class NumberChecker {
    public static void main(String[] s){
        int[] in = new int[]{1456,345671,43218,123};
        System.out.println(checker(in));

    }

    public static String checker(int[] in){
        Arrays.sort(in);
        String out="";
        for(int i:in){
            String c=""+i;
            if(c.contains("1") && c.contains("2") && c.contains("3")){
                out=out+c+",";
            }
        }
        if(!out.isEmpty())
            return out.substring(0,out.length()-1);
        return "-1";
    }
}
