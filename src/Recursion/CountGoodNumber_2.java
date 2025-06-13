package Recursion;

public class CountGoodNumber_2 {
    //https://leetcode.com/problems/count-good-numbers/description/
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
