package kimlamdo.my_project_backend.service.review;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import kimlamdo.my_project_backend.dao.CustomerRepository;
import kimlamdo.my_project_backend.dao.ProductRepository;
import kimlamdo.my_project_backend.dao.ReviewRepository;
import kimlamdo.my_project_backend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ObjectMapper objectMapper;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public ReviewServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(JsonNode jsonData) {
        try {
            Review reviewData = objectMapper.treeToValue(jsonData, Review.class);

            int rating = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("rating"))));

            int customerId = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("customerId"))));
            Optional<Customer> customer = customerRepository.findById(customerId);
            reviewData.setCustomer(customer.get());

            int productId = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("productId"))));
            Optional<Product> product = productRepository.findById(productId);
            product.get().setAverageRating((product.get().getAverageRating() * product.get().getRatingCount() + rating) / (product.get().getRatingCount() + 1));
            product.get().setRatingCount(product.get().getRatingCount() + 1);
            reviewData.setProduct(product.get());

            reviewData.setReviewTime(LocalDateTime.now());
            reviewRepository.save(reviewData);
            productRepository.save(product.get());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> update(JsonNode jsonData) {
        try {
            Review reviewData = objectMapper.treeToValue(jsonData, Review.class);

            int reviewId = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("reviewId"))));
            Review review = reviewRepository.findById(reviewId).get();
            if (review == null) {
                return ResponseEntity.notFound().build(); // Trả về 404 nếu không tìm thấy đánh giá
            }

            int oldRating = review.getRating();
            review.setComment(reviewData.getComment());
            review.setReviewTime(LocalDateTime.now());
            review.setRating(reviewData.getRating());
            // Cập nhật thời gian đánh giá
            review.setReviewTime(LocalDateTime.now());

            int productId = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("productId"))));
            Optional<Product> product = productRepository.findById(productId);

            // Xử lý averageRating và ratingCount của product
            int newRating = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("rating"))));
            product.get().setAverageRating((product.get().getAverageRating() * product.get().getRatingCount() - oldRating + newRating) / (product.get().getRatingCount()));

            reviewRepository.save(review);
            productRepository.save(product.get());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> delete(JsonNode jsonData) {
        try {
            Review reviewData = objectMapper.treeToValue(jsonData, Review.class);

            int reviewId = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("reviewId"))));
            Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
            if (reviewOptional.isEmpty()) {
                return ResponseEntity.notFound().build(); // Review không tồn tại
            }
            Review review = reviewOptional.get();

            int productId = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("productId"))));
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isEmpty()) {
                return ResponseEntity.notFound().build(); // Product không tồn tại
            }
            Product product = productOptional.get();
            System.out.println(productId);

            if (product.getRatingCount() > 1) {
                product.setAverageRating((product.getAverageRating() * product.getRatingCount() - reviewData.getRating()) / (product.getRatingCount() - 1));
                product.setRatingCount(product.getRatingCount() - 1);
            } else {
                product.setAverageRating(0);
                product.setRatingCount(0);
            }

            // Xóa review
            reviewRepository.delete(review);

            // Lưu lại thông tin của product
            productRepository.save(product);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    private String formatStringByJson(String json) {
        return json.replaceAll("\"", "");
    }
}
