package JavaConcepts.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinUsingStream_5 {
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 4};
        List<Integer> ls = Arrays.stream(arr).boxed().toList();
        int min=ls.stream().min(Comparator.comparingInt(Integer::intValue)).get();

        System.out.println("Minimum value: " + min);

        int max=ls.stream().max(Comparator.comparingInt(Integer::intValue)).get();

        System.out.println("Maximum value: " + max);

        //min without comparator
        double N_min=ls
                                    .stream()
                                    .mapToDouble(Integer::doubleValue)
                                    .min().orElse(0.0);
        System.out.println("Minimum value without comparator: " + N_min);

        double N_max=ls
                .stream()
                .mapToDouble(Integer::doubleValue)
                .max().orElse(0.0);
        System.out.println("Maximum value without comparator: " + N_max);
    }
}
