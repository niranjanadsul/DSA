package NagaroTest;

public class NeatString {
    public String neatString(String a, int n, int cl, int cu){
        String neat = "";
        return neat;
    }
    public boolean isSameChar(char a, char b){
        if(a==b || a-b==32 || a-b == -32)
            return true;
        return false;
    }
    public static void main(String[] args) {
        String s = "aA";
        System.out.println(s.charAt(0)+0);
        System.out.println(s.charAt(1)+0);
        System.out.println(s.charAt(0)-s.charAt(1));
        System.out.println(s.charAt(1)-s.charAt(0));
        NeatString neatString = new NeatString();
        System.out.println(neatString.isSameChar(s.charAt(0),s.charAt(1)));
    }
}
