package JavaConcepts.streams;

import java.util.Arrays;

public class StreamsReduce_12 {
    public static void main(String[] args) {

        //reduce is a terminal operation, it returns a single value
        //reduce is a stateful operation, it needs to see the entire stream to determine the result
        //reduce uses equals() and hashCode() methods to determine the result
        //reduce is a short-circuiting operation, it can terminate the stream processing early if it finds enough elements
        //reduce has 2 parameters, the first parameter is the identity value, the second parameter is the accumulator function
        //identity value is the initial value of the result, it is used when the stream is empty
        //accumulator function is a function that takes 2 parameters, the first parameter is the result so far,
        // the second parameter is the current element of the stream, it returns the new result

        System.out.println("Print sum of numbers**********");
        int[] arr = {5, 2, 8, 1, 4};
        int sum = Arrays.stream(arr).reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        System.out.println("Print product of numbers**********");
        int product = Arrays.stream(arr).reduce(1, (a, b) -> a * b);
        System.out.println(product);

        System.out.println("Print max of numbers**********");
        int max = Arrays.stream(arr).reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b);
        System.out.println(max);

        System.out.println("Print min of numbers**********");
        int min = Arrays.stream(arr).reduce(Integer.MAX_VALUE, (a, b) -> a < b ? a : b);
        System.out.println(min);
    }
}
