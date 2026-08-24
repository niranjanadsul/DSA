package JavaConcepts.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamInternals_15 {
    public static void main(String[] args) {
        //lets us understand the internals of collect(Collectors.toList()) method
        Integer[] arr = {1, 2, 3, 4, 5};
        List<Integer> bigList = Arrays.stream(arr).collect(()->new ArrayList<>(),
                (list, element) -> list.add(element),
                (list1, list2) -> list1.addAll(list2));
        System.out.println(bigList);

         bigList = Arrays.stream(arr).collect(ArrayList::new,
                ArrayList::add,
                ArrayList::addAll);

        //collect has 3 arguments
        //first argument is a supplier, it provides a new empty collection to collect the elements
        //second argument is an accumulator, it adds an element to the collection
        //third argument is a combiner, it combines two collections into one
    }
}
