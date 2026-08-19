package JavaConcepts.streams;

import java.util.Arrays;
import java.util.List;

public class StreamFiltering_7 {
    public static void main(String[] args) {
        System.out.println("**********");
        //filter is an intermediate operation, it returns a stream
        //filter is a stateless operation, it does not need to see the entire stream to determine the filtered elements
        //filter uses predicate to determine the filtered elements
        //filter is a short-circuiting operation, it can terminate the stream processing early if it finds enough filtered elements

        System.out.println("Print numbers less than 20**********");
        int[] arr = {5, 2, 8, 1, 4, 2, 5, 8};
        List<Integer> ls= Arrays.stream(arr).filter(number->number<5).boxed().toList();
        System.out.println(ls);
    }
}
