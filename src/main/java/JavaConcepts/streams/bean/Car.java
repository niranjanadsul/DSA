package JavaConcepts.streams.bean;

import java.util.List;

public class Car {
    private String name;
    private String color;
    private int price;

    public Car(String name, String color, int price) {
        this.name = name;
        this.color = color;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static List<Car> getCars() {
        return List.of(
                new Car("Toyota", "Red", 20000),
                new Car("Honda", "Blue", 18000),
                new Car("Ford", "Black", 25000),
                new Car("Chevrolet", "White", 22000),
                new Car("Nissan", "Silver", 19000),
                new Car("BMW", "Black", 35000),
                new Car("Mercedes", "White", 40000),
                new Car("Audi", "Red", 45000),
                new Car("Volkswagen", "Blue", 30000),
                new Car("Hyundai", "Silver", 17000),
                new Car("Kia", "Red", 16000)
        );
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}
