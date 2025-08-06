package Paypal;

public class EscapedChar {

    public static int escapedCharacters(String s){
        int count=0;
        int i=0;
        while(i<s.length()){
            if(s.charAt(i)=='#'){
                int end=s.indexOf('#',i+1);
                if(end==-1){
                    break;
                }
                String part=s.substring(i+1,end);
                for(int j=1;j<part.length();j++){
                    if(part.charAt(j-1)=='!' && Character.isLowerCase(part.charAt(j))){
                        count++;
                    }
                }
                i=end+1;
            }else{
                i++;
            }
        }

        return count;
    }

    public static void main(String[] args){
       int ans= EscapedChar.escapedCharacters("#ab!c#de!f");
       System.out.println(ans);
    }
}
