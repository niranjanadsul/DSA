package Recursion;

public class PowerX_N_1 {
    public static long mod = 1000000000+7;
    //refer the One note for Binary exponentiation algo
    private static double power(double x, int n){
        if(n==0)
            return 1;
        double res = power(x, n/2);
        if(n%2==0)
            return (res*res)%mod;
        else
            return (x*(res*res)%mod)%mod;
    }

    public static double pow(double x, int n) {
        return n<0?1/power(x,n):power(x,n);
    }

    public static void main(String[] args){
        System.out.println(pow(4,2));
    }
}
