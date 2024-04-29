package kimlamdo.my_project_backend.service.review;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface ReviewService {
    public ResponseEntity<?> save(JsonNode jsonData);

    public ResponseEntity<?> update(JsonNode jsonData);

    public ResponseEntity<?> delete(JsonNode jsonData);
}