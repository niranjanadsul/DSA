package JavaConcepts.streams;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class StreamMap_8 {
    public static void main(String[] args) {
        System.out.println("**********");
        //map is an intermediate operation, it returns a stream
        //map is a stateless operation, it does not need to see the entire stream to determine the mapped elements
        //map uses function to determine the mapped elements
        //map is a short-circuiting operation, it can terminate the stream processing early if it finds enough mapped elements

        System.out.println("Print square of numbers**********");
        Integer[] arr = {5, 2, 8, 1, 4};
        Function<Integer,Integer> square= number->number*number;//method reference can also be used here
        //Function takes interger as input and returns integer as output
        var ls= Arrays.stream(arr).map(square).collect(Collectors.toList());
        System.out.println(ls);


        //lets learn mapToDouble
        Integer[] carPrices = {10000, 20000, 30000, 40000, 50000};
        //calculate average price of cars using mapToDouble
        double averagePrice = Arrays.stream(carPrices)
                .mapToDouble(price -> price).average().orElse(0.0);
        System.out.println("Average price of cars: " + averagePrice);

        //explanation of above code
        //mapToDouble is an intermediate operation, it returns a DoubleStream
        //average is a terminal operation, it returns an OptionalDouble
        //orElse is a method of OptionalDouble, it returns the value if present, otherwise returns the default value
    }
}
