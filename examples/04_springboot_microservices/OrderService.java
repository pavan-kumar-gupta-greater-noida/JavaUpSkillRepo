import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final Map<Long, Order> orders = new HashMap<>();
    private long sequence = 1L;

    public Order createOrder(OrderRequest request) {
        if (request.quantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        Order order = new Order(sequence++, request.customerId(), request.productId(), request.quantity(), "CREATED");
        orders.put(order.id(), order);
        return order;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders.values());
    }
}
