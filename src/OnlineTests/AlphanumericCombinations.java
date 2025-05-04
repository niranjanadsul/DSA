package OnlineTests;

public class AlphanumericCombinations {
    public static void main(String[] s){
        String in="123";
        int[] arr = new int[1];
        arr[0]=0;
        alpha(0,1,in,arr);
        System.out.println(arr[0]);
    }

    public static void alpha(int s, int e, String in, int[] arr){
        if(s==in.length()){
            arr[0]=arr[0]+1;
            return;
        }
        if(e>in.length()){
            return;
        }
        int num=Integer.parseInt(in.substring(s,e));
        if(num<1 || 26<num)
            return;
        //take partition
        alpha(e,e+1,in, arr);

        //not take partition
        alpha(s,e+1,in,arr);
    }
}
