package kimlamdo.my_project_backend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import kimlamdo.my_project_backend.service.order.OrderService;
import kimlamdo.my_project_backend.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping("/add-review")
    public ResponseEntity<?> save (@RequestBody JsonNode jsonData) {
        try{
            return reviewService.save(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update-review")
    public ResponseEntity<?> update (@RequestBody JsonNode jsonData) {
        try{
            return reviewService.update(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/delete-review")
    public ResponseEntity<?> delete (@RequestBody JsonNode jsonData) {
        try{
            return reviewService.delete(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
