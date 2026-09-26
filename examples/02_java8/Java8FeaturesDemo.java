import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Java8FeaturesDemo {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Lambda and stream examples
        List<String> filtered = names.stream()
                .filter(name -> name.startsWith("A") || name.startsWith("C"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Filtered names: " + filtered);

        // Optional usage
        Optional<String> maybeName = Optional.ofNullable("Alice");
        maybeName.ifPresent(name -> System.out.println("Optional value: " + name));

        // Default method example
        Vehicle car = new Car();
        System.out.println("Vehicle default info: " + car.getInfo());

        // DateTime API example
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusWeeks(1);
        System.out.println("Today: " + today + " | Next week: " + nextWeek);

        // Parallel stream example
        List<Integer> numbers = IntStream.rangeClosed(1, 10)
                .boxed()
                .collect(Collectors.toList());

        int sum = numbers.parallelStream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Parallel sum: " + sum);
    }

    interface Vehicle {
        default String getInfo() {
            return "Vehicle is ready";
        }
    }

    static class Car implements Vehicle {
    }
}
