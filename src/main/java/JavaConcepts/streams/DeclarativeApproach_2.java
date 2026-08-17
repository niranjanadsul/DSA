package JavaConcepts.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeclarativeApproach_2 {
    public static List<Integer> declarative(){
        //find numbers less than 20
        //get first 5
        List<Integer> ls=List.of(2,3,4,513,21,25,3,27,88,12,11,8,9);
        return ls.stream().filter(x->x<20).limit(5).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        declarative();
    }
}
