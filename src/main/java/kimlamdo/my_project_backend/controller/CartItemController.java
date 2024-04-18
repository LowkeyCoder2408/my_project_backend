package kimlamdo.my_project_backend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import kimlamdo.my_project_backend.service.cartItem.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart-item")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/add-item")
    public ResponseEntity<?> add(@RequestBody JsonNode jsonData) {
        try {
            return cartItemService.save(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update-item")
    private ResponseEntity<?> update(@RequestBody JsonNode jsonData) {
        try {
            cartItemService.update(jsonData);
            return ResponseEntity.ok("Thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
