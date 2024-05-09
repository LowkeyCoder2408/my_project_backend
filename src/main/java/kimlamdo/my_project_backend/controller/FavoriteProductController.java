package kimlamdo.my_project_backend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import kimlamdo.my_project_backend.dao.CustomerRepository;
import kimlamdo.my_project_backend.dao.FavoriteProductRepository;
import kimlamdo.my_project_backend.dao.ProductRepository;
import kimlamdo.my_project_backend.entity.Customer;
import kimlamdo.my_project_backend.entity.FavoriteProduct;
import kimlamdo.my_project_backend.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/favorite-product")
public class FavoriteProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FavoriteProductRepository favoriteProductRepository;

    @GetMapping("/get-favorite-product/{customerId}")
    public ResponseEntity<?> getAllFavoriteProductByCustomerId(@PathVariable Integer customerId) {
        try {
            Customer customer = customerRepository.findById(customerId).get();
            List<FavoriteProduct> favoriteProductList = favoriteProductRepository.findFavoriteProductsByCustomer(customer);
            List<Integer> favoriteProductIdsList = new ArrayList<>();
            for (FavoriteProduct favoriteProduct : favoriteProductList) {
                favoriteProductIdsList.add(favoriteProduct.getProduct().getId());
            }

            for (Integer productId : favoriteProductIdsList) {
                System.out.print(productId + " ");
            }

            return ResponseEntity.ok().body(favoriteProductIdsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/add-favorite-product")
    public ResponseEntity<?> save(@RequestBody JsonNode jsonNode) {
        try {
            int productId = Integer.parseInt(formatStringByJson(jsonNode.get("productId").toString()));
            int customerId = Integer.parseInt(formatStringByJson(jsonNode.get("customerId").toString()));

            Product product = productRepository.findById(productId).get();
            Customer customer = customerRepository.findById(customerId).get();

            FavoriteProduct favoriteProduct = FavoriteProduct.builder().product(product).customer(customer).build();

            favoriteProductRepository.save(favoriteProduct);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-favorite-product")
    public ResponseEntity<?> remove(@RequestBody JsonNode jsonNode) {
        try {
            int productId = Integer.parseInt(formatStringByJson(jsonNode.get("productId").toString()));
            int customerId = Integer.parseInt(formatStringByJson(jsonNode.get("customerId").toString()));

            Product product = productRepository.findById(productId).get();
            Customer customer = customerRepository.findById(customerId).get();

            FavoriteProduct favoriteProduct = favoriteProductRepository.findFavoriteProductByProductAndCustomer(product, customer);

            favoriteProductRepository.delete(favoriteProduct);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    private String formatStringByJson(String json) {
        return json.replaceAll("\"", "");
    }
}
