import java.util.*;
import java.util.stream.*;

public class StreamInterviewExamples {
    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product(1, "Laptop", 1200.00, true),
                new Product(2, "Phone", 850.00, true),
                new Product(3, "Mouse", 25.00, false),
                new Product(4, "Monitor", 320.00, true),
                new Product(5, "Keyboard", 60.00, false)
        );

        // 1. Filter + map + sum
        double activeProductsTotal = products.stream()
                .filter(Product::inStock)
                .mapToDouble(Product::price)
                .sum();
        System.out.println("Active product total: " + activeProductsTotal);

        // 2. Grouping by stock status
        Map<Boolean, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(Product::inStock));
        System.out.println("Grouped by stock: " + grouped);

        // 3. Top 2 priciest products
        List<Product> topTwo = products.stream()
                .sorted(Comparator.comparingDouble(Product::price).reversed())
                .limit(2)
                .toList();
        System.out.println("Top 2 expensive: " + topTwo);

        // 4. Find first matching product name
        Optional<Product> optional = products.stream()
                .filter(p -> p.name().equalsIgnoreCase("Phone"))
                .findFirst();
        optional.ifPresent(product -> System.out.println("Found: " + product));

        // 5. Parallel stream caution: stateful operations may fail if not thread-safe
        List<Integer> list = IntStream.rangeClosed(1, 100)
                .boxed()
                .collect(Collectors.toList());

        int evenCount = list.parallelStream()
                .filter(n -> n % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Even sum: " + evenCount);
    }

    record Product(int id, String name, double price, boolean inStock) { }
}
