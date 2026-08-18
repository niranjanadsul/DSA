package JavaConcepts.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinUsingStream {
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 4};
        List<Integer> ls = Arrays.stream(arr).boxed().toList();
        int min=ls.stream().min(Comparator.comparingInt(Integer::intValue)).get();

        System.out.println("Minimum value: " + min);

        int max=ls.stream().max(Comparator.comparingInt(Integer::intValue)).get();

        System.out.println("Maximum value: " + max);
    }
}
