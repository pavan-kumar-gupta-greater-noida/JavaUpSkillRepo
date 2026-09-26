import java.util.*;
import java.util.concurrent.*;

public class TechLeadScenarioDemo {
    public static void main(String[] args) throws Exception {
        // Scenario: thread-safe inventory check and update
        InventoryService service = new InventoryService();
        service.addItem("SKU-100", 5);

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> service.reserve("SKU-100", 2));
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> service.reserve("SKU-100", 3));

        CompletableFuture.allOf(f1, f2).join();
        System.out.println("Remaining stock: " + service.getStock("SKU-100"));

        // Scenario: timeout-safe API call pattern
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<String> status = executor.submit(() -> {
            Thread.sleep(1500);
            return "Healthy";
        });

        try {
            String result = status.get(1, TimeUnit.SECONDS);
            System.out.println("API status: " + result);
        } catch (TimeoutException e) {
            status.cancel(true);
            System.out.println("Call timed out and was cancelled.");
        }

        executor.shutdown();
    }

    static class InventoryService {
        private final Map<String, Integer> inventory = new ConcurrentHashMap<>();

        public void addItem(String sku, int quantity) {
            inventory.merge(sku, quantity, Integer::sum);
        }

        public int reserve(String sku, int quantity) {
            synchronized (inventory) {
                int current = inventory.getOrDefault(sku, 0);
                if (current < quantity) {
                    return 0;
                }
                inventory.put(sku, current - quantity);
                return quantity;
            }
        }

        public int getStock(String sku) {
            return inventory.getOrDefault(sku, 0);
        }
    }
}
