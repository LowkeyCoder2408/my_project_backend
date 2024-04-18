package kimlamdo.my_project_backend.service.cartItem;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface CartItemService {

    public ResponseEntity<?> save(JsonNode jsonNode);

    public ResponseEntity<?> update(JsonNode jsonNode);

}
