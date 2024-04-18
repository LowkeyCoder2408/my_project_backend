package kimlamdo.my_project_backend.controller;


import com.fasterxml.jackson.databind.JsonNode;
import kimlamdo.my_project_backend.entity.Product;
import kimlamdo.my_project_backend.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(path = "/add-product")
    public ResponseEntity<?> save(@RequestBody JsonNode jsonData) {
        try {
            return productService.save(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Đã xảy ra lỗi");
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/update-product")
    public ResponseEntity<?> update(@RequestBody JsonNode jsonData) {
        try {
            return productService.update(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Đã xảy ra lỗi");
            return ResponseEntity.badRequest().build();
        }
    }
}
