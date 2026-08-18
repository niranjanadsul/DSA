package JavaConcepts.streams;

import java.util.stream.IntStream;

public class IntstreamIterate_4 {
    public static void main(String[] args) {
        System.out.println("**********");
        //iterate is infinite stream, so we need to limit it
        //iterate takes a seed and a unary operator
        //unary operator is a function that takes one argument and returns a value of the same type

        System.out.println("Print 20 even numbers using iterate**********");
        IntStream.iterate(0, operand -> operand+1).filter(number-> number%2==0)
                .limit(20).forEach(System.out::println);
    }
}
