package kimlamdo.my_project_backend.service.product;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    public ResponseEntity<?> save(JsonNode productJson);

    public ResponseEntity<?> update(JsonNode productJson);
}
