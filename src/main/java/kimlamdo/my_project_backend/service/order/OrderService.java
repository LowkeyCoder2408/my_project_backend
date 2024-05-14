package kimlamdo.my_project_backend.service.order;

import com.fasterxml.jackson.databind.JsonNode;
import kimlamdo.my_project_backend.entity.OrderStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface OrderService {
    public ResponseEntity<?> save(JsonNode jsonData);

    public ResponseEntity<?> update(JsonNode jsonData);

    public ResponseEntity<?> cancel(JsonNode jsonData);

    public List<Integer> getDistinctOrderMonths();

    public Map<OrderStatus, Double> calculateOrderPercentageByStatus();
}
