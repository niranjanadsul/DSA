package JavaConcepts.streams;

import java.util.Arrays;

public class StreamCount_10 {
    public static void main(String[] args) {
        System.out.println("**********");
        //count is a terminal operation, it returns a long
        //count is a stateless operation, it does not need to see the entire stream to determine the count
        //count uses predicate to determine the count
        //count is a short-circuiting operation, it can terminate the stream processing early if it finds enough elements

        System.out.println("Print count of numbers less than 5**********");
        Integer[] arr = {5, 2, 8, 1, 4, 2, 5, 8};
        long count= Arrays.stream(arr).filter(number->number<5).count();
        System.out.println(count);
    }
}
