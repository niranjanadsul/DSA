package Array.TwoPointer;

import java.util.Arrays;

public class AssignCookie_1 {
    // n size of g and m size of s
    // Time complexity = O(nlogn(sorting g) + mlogm(sorting s) + nlogm(for each element in g search in s ))
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i=0,j=0,count=0;
        while(i<g.length && j<s.length){
            if(g[i]<=s[j]){
                count++;
                i++;
                j++;
            }else{
                j++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        AssignCookie_1 assignCookie1=new AssignCookie_1();
        int count;
        count=assignCookie1.findContentChildren(new int[]{1,2,3},new int[]{1,1});//1
        System.out.println(count);
    }
}
