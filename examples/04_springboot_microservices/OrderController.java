import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        Order saved = orderService.createOrder(request);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getOrders();
    }
}

record OrderRequest(String customerId, String productId, int quantity) {}
record Order(Long id, String customerId, String productId, int quantity, String status) {}
