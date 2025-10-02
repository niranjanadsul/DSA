package GoldmanSachTest;

public class SecureConversation {

    public static String secureChannel(int operation, String k, String message){
        int key = -1;
        String out = "";
        try{
            if(message.isEmpty())
                throw new Exception();
            key = Integer.parseInt(k);
            if(operation == 1){
                out =  encode(message,key);
            }else{
                out = decode(message, key);
            }
            if(out.isEmpty())
                throw new Exception();
        }catch (Exception e){
            return "-1";
        }
        return out;
    }
    public static String encode(String input, int key){
        int len = Math.min(input.length(), (key+"").length());
        String encodedOutput="";
        int i=0;
        for(i=0;i<len;i++){
            String inChar=input.charAt(i)+"";
            int k = Integer.parseInt((key+"").charAt(i)+"");
            for(int j=0;j<k;j++){
                encodedOutput+=inChar;
            }
        }
        if(i<=input.length())
            encodedOutput+=input.substring(i);
        return encodedOutput;
    }

    public static String decode(String encodedMsg, int key){
        int klen = (key+"").length();
        int j = 0;
        String output= "";
        for(int i = 0;i<klen;i++) {
            int k = Integer.parseInt((key + "").charAt(i) + "");
            if(j<encodedMsg.length()){
                output+=encodedMsg.charAt(j);
                j+=k;
            }
        }
        if(j<encodedMsg.length())
            output+=encodedMsg.substring(j);
        return output;
    }
    public static void main(String[] args) {
        String e = secureChannel(1,"123","open");
        System.out.println(e);
        String d = secureChannel(2,"123",e);
        System.out.println(d);

        d = secureChannel(2,"1234","Opp");
        System.out.println(d);
        e = secureChannel(1,"abc","Opened");
        System.out.println(e);
        d = secureChannel(2,"123","Open");
        System.out.println(d);
    }
}
