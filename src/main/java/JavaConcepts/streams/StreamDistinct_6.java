package JavaConcepts.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamDistinct_6 {
    public static void main(String[] args) {
        System.out.println("**********");
        //distinct is an intermediate operation, it returns a stream
        //distinct is a stateful operation, it needs to see the entire stream to determine the distinct elements
        //distinct uses equals() and hashCode() methods to determine the distinct elements
        //distinct is a short-circuiting operation, it can terminate the stream processing early if it finds enough distinct elements

        System.out.println("Print distinct numbers**********");
        int[] arr = {5, 2, 8, 1, 4, 2, 5, 8};
        List<Integer> ls= Arrays.stream(arr).distinct().boxed().collect(Collectors.toList());
        System.out.println(ls);

        //we can also get distinct elements by using collectors.toSet() but it will not maintain the order of elements
        Set<Integer> ls2= Arrays.stream(arr).boxed().collect(Collectors.toSet());
        System.out.println(ls2);
    }
}
