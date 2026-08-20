package JavaConcepts.streams;

import java.util.Arrays;

public class StreamFindAny_9 {
    public static void main(String[] args) {
        System.out.println("**********");
        //findAny is a terminal operation, it returns an Optional
        //findAny is a short-circuiting operation, it can terminate the stream processing early if it finds any element
        //findAny is useful in parallel streams, it can return any element from the stream without waiting
        // for the entire stream to be processed

        System.out.println("Print any number**********");
        Integer[] arr = {5, 2, 8, 1, 4};
        Integer anyNumber= Arrays.stream(arr).findAny().get();
        System.out.println(anyNumber);

        //find number <3
        Integer numberLessThan3= Arrays.stream(arr).filter(number->number<3).findAny().orElse(null);
        System.out.println(numberLessThan3);

        //findFirst is a terminal operation, it returns an Optional
        //findFirst is a short-circuiting operation, it can terminate the stream processing early
        //findFirst is useful in sequential streams, it can return the first element from the stream without waiting
        System.out.println("Print first number**********");
        Integer firstNumber= Arrays.stream(arr).findFirst().get();
        System.out.println(firstNumber);
        //find number <3
         numberLessThan3= Arrays.stream(arr).filter(number->number<3).findFirst().orElse(null);
        System.out.println(numberLessThan3);

        //difference between findAny and findFirst
        //findAny can return any element from the stream, it does not guarantee the order of elements, it is useful in parallel streams
        //findAny is extremely non-deterministic in parallel streams, it can return different elements in different runs, on same data

        //findFirst returns the first element from the stream
        //findFirst is deterministic in sequential streams, it always returns the same element in different runs, on same data


    }
}
