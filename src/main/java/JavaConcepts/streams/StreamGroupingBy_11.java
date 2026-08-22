package JavaConcepts.streams;

import JavaConcepts.streams.bean.Car;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamGroupingBy_11 {
    public static void main(String[] args) {

        //groupingBy is a terminal operation, it returns a Map
        //groupingBy is a stateful operation, it needs to see the entire stream to determine the groups
        //groupingBy uses equals() and hashCode() methods to determine the groups
        //groupingBy is a short-circuiting operation, it can terminate the stream processing early if it finds enough groups

        System.out.println("Print grouping by color**********");
        List<Car> cars = Car.getCars();
        Map<String,List<Car>> colorToCarsMap = cars.stream().collect(Collectors.groupingBy(Car::getColor));
        colorToCarsMap.forEach((color, carsList) -> {
            System.out.println("Color: " + color);
            carsList.forEach(car -> System.out.println(car));
        });

        //grouping and counting the number of cars in each group
        System.out.println("Print grouping by color and counting the number of cars in each group**********");
        Map<String, Long> colorToCarsCountMap = cars.stream().collect(Collectors.groupingBy(Car::getColor, Collectors.counting()));
        colorToCarsCountMap.forEach((color, count) -> {
            System.out.println("Color: " + color + ", Count: " + count);
        });
    }
}
