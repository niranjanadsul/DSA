package JavaConcepts.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class FlatMap_13 {
    public static void main(String[] args) {

        //flatMap is an intermediate operation, it returns a stream
        //flatMap is a stateless operation, it does not need to see the entire stream to determine the output
        //flatMap is a short-circuiting operation, it can terminate the stream processing early if it finds enough elements
        //flatMap needs a function that takes an element and returns a stream of elements, it flattens the streams into a single stream
        //this function tells how to flatten the streams, it can be a lambda expression or a method reference

        System.out.println("Print flatMap**********");
        List<List<String>> ls = List.of(List.of("a", "b"), List.of("c", "d"));
        List<String> flatArr = ls.stream()
                .flatMap(List::stream).collect(Collectors.toList());
        System.out.println(flatArr);
    }
}
