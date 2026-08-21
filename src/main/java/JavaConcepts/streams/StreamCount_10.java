package JavaConcepts.streams;

import java.math.BigDecimal;
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

        //calculate average
        System.out.println("Print average of numbers**********");
        double average= Arrays.stream(arr).mapToDouble(Integer::intValue).average().orElse(0.0);
        System.out.println(average);

        //calculate sum
        System.out.println("Print sum of numbers**********");
        Double sum= Arrays.stream(arr).mapToDouble(Integer::intValue).sum();
        System.out.println(sum);
        BigDecimal sumBigDecimal = BigDecimal.valueOf(sum);
        System.out.println("Sum as BigDecimal: " + sumBigDecimal);

        //calculate statistics
        System.out.println("Print statistics of numbers**********");
        var statistics= Arrays.stream(arr).mapToDouble(Integer::intValue).summaryStatistics();
        System.out.println("Count:"+statistics.getCount());
        System.out.println("Sum:"+statistics.getSum());
        System.out.println("Average:"+statistics.getAverage());
        System.out.println("Max:"+statistics.getMax());
        System.out.println("Min:"+statistics.getMin());
    }
}
