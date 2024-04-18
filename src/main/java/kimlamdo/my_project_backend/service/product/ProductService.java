package kimlamdo.my_project_backend.service.product;

import com.fasterxml.jackson.databind.JsonNode;
import kimlamdo.my_project_backend.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    public ResponseEntity<?> save(JsonNode productJson);

    public ResponseEntity<?> update(JsonNode productJson);

//    List<Product> findTop4ProductsByPriceDifference();
}
