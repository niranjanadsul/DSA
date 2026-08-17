package JavaConcepts.streams;

import java.util.ArrayList;
import java.util.List;

public class ImperativeApproach_1 {
    public static List<Integer> imperative(){
        //find numbers less than 20
        //get first 5
        List<Integer> ls=List.of(2,3,4,513,21,25,3,27,88,12,11,8,9);

        ArrayList<Integer> lessThan20=new ArrayList<>();
        int count=5;
        for (int i:ls){
            if(count==0)
                break;
            if(i<20) {
                lessThan20.add(i);
                count--;
            }
        }
        return lessThan20;
    }
    public static void main(String[] args) {
        imperative();
    }
}
