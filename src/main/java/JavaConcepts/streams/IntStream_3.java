package JavaConcepts.streams;

import java.util.List;
import java.util.stream.IntStream;

public class IntStream_3 {
    public static void main(String[] args) {
        System.out.println("For loop**********");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        System.out.println("exclusive**********");
        IntStream.range(0, 10).forEach(System.out::println);//range is exclusive of the last number
        System.out.println("inclusive**********");
        IntStream.rangeClosed(0, 10).forEach(System.out::println);//rangeClosed is inclusive of the last number
        System.out.println("**********");

        List<String> ls=List.of("a","b","c","d");
        IntStream.range(0, ls.size()).forEach(i-> System.out.println(ls.get(i)));
        System.out.println("**********");
        IntStream.rangeClosed(0, ls.size()-1).forEach(i-> System.out.println(ls.get(i)));
        System.out.println("**********");

        ls.forEach(System.out::println);
    }
}
