package Recursion;

public class CountGoodNumber_2 {
    //https://leetcode.com/problems/count-good-numbers/description/
    /*
    * A digit string is good if the digits (0-indexed) at even indices are even and
    * the digits at odd indices are prime (2, 3, 5, or 7).

    For example, "2582" is good because the digits (2 and 8) at even positions are even and
    * the digits (5 and 2) at odd positions are prime. However, "3245" is not good because 3
    * is at an even index but is not even.
    Given an integer n, return the total number of good digit strings of length n. Since the
    * answer may be large, return it modulo 109 + 7.

    A digit string is a string consisting of digits 0 through 9 that may contain leading zeros.



    Example 1:

    Input: n = 1
    Output: 5
    Explanation: The good numbers of length 1 are "0", "2", "4", "6", "8".
    Example 2:

    Input: n = 4
    Output: 400
    Example 3:

    Input: n = 50
    Output: 564908303*/
    int mod=1000000000+7;
    public int countGoodNumbers(long n) {
        //if the n is even then we have n/2 even index and n/2 odd index for prime digits
        //if n is odd then we have (n+1)/2 even index and n/2 odd index for prime digits
        //even indexes can contain one of the 5 even digits
        //odd indexes can contain one of the 4 prime digits
        return (int) ((pow(5, (n+1)/2) * pow(4, n/2))%mod);

    }

    private long power(long x, long n){
        if(n==0)
            return 1;
        long res = power(x, n/2);
        res =(res*res)%mod;
        if(n%2==0)
            return res;
        else
            return (x*res)%mod;
    }

    public long pow(long x, long n) {
        return n<0?1/power(x,n):power(x,n);
    }

    public static void main(String[] arg){
        CountGoodNumber_2 countGoodNumber2 = new CountGoodNumber_2();
        System.out.println(countGoodNumber2.countGoodNumbers(50));
    }
}
