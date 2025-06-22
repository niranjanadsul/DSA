package Stack;

import java.util.Stack;

public class BooleanExpression_1 {
    public boolean parseBoolExpr(String expression) {
        Stack<Character> st = new Stack<>();
        for(char c:expression.toCharArray()){
            if(c!=',' && c!=')'){
                st.push(c);
            }else if(c==')'){
                boolean hasTrue = false;
                boolean hasFalse = false;
                while(st.peek()!='('){
                    if('t'==st.pop()){
                        hasTrue = true;
                    }else{
                        hasFalse = true;
                    }
                }
                st.pop();//pop (
                char op = st.pop(); //pop opr
                if('&'==op){
                    if(hasFalse){
                        st.push('f');
                    }else if(hasTrue){
                        st.push('t');
                    }
                } else if('|' == op){
                    if(hasTrue){
                        st.push('t');
                    }else if(hasFalse){
                        st.push('f');
                    }
                } else if('!'== op){
                    if(hasTrue){
                        st.push('f');
                    }else if(hasFalse){
                        st.push('t');
                    }
                }
            }

        }
        return 't' == st.pop();
    }

    public static void main(String[] args) {
        BooleanExpression_1 booleanExpression1=new BooleanExpression_1();
        System.out.println(booleanExpression1.parseBoolExpr("&(|(f))"));
        System.out.println(booleanExpression1.parseBoolExpr("|(f,f,f,t)"));
    }
}
