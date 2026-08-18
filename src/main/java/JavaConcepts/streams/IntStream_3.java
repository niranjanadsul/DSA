package JavaConcepts.streams;

import java.util.stream.IntStream;

public class IntStream_3 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        System.out.println("**********");
        IntStream.range(0, 10).forEach(System.out::println);//range is exclusive of the last number
        System.out.println("**********");
        IntStream.rangeClosed(0, 10).forEach(System.out::println);//rangeClosed is inclusive of the last number
    }
}
