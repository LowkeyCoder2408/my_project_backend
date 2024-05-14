package kimlamdo.my_project_backend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import kimlamdo.my_project_backend.entity.OrderStatus;
import kimlamdo.my_project_backend.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/add-order")
    public ResponseEntity<?> save(@RequestBody JsonNode jsonData) {
        try {
            return orderService.save(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update-order")
    public ResponseEntity<?> update(@RequestBody JsonNode jsonData) {
        try {
            return orderService.update(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/cancel-order")
    public ResponseEntity<?> cancel(@RequestBody JsonNode jsonNode) {
        try {
            return orderService.cancel(jsonNode);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/distinct-months")
    public List<Integer> getDistinctOrderMonths() {
        return orderService.getDistinctOrderMonths();
    }

    @GetMapping("/status-percentage")
    public Map<OrderStatus, Double> getOrderStatusPercentage() {
        return orderService.calculateOrderPercentageByStatus();
    }
}

