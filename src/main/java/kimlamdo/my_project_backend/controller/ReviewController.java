package kimlamdo.my_project_backend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import kimlamdo.my_project_backend.dao.ProductRepository;
import kimlamdo.my_project_backend.dao.ReviewRepository;
import kimlamdo.my_project_backend.entity.Product;
import kimlamdo.my_project_backend.entity.Review;
import kimlamdo.my_project_backend.service.order.OrderService;
import kimlamdo.my_project_backend.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping("/add-review")
    public ResponseEntity<?> save(@RequestBody JsonNode jsonData) {
        try {
            return reviewService.save(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update-review")
    public ResponseEntity<?> update(@RequestBody JsonNode jsonData) {
        try {
            return reviewService.update(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/delete-review")
    public ResponseEntity<?> delete(@RequestBody JsonNode jsonData) {
        try {
            return reviewService.delete(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

//    @PostMapping("/get-review")
//    public ResponseEntity<?> getReview(@RequestBody JsonNode jsonNode) {
//        try {
//            int productId = Integer.parseInt(formatStringByJson(String.valueOf(jsonNode.get("productId"))));
//
//            Product product = productRepository.findById(productId).get();
//            List<OrderDetail> orderDetailList = orderDetailRepository.findOrderDetailsByOrder(order);
//            for (OrderDetail orderDetail : orderDetailList) {
//                if (orderDetail.getBook().getIdBook() == book.getIdBook()) {
//                    Review review = reviewRepository.findReviewByOrderDetail(orderDetail);
//                    Review reviewResponse = new Review(); // Trả review luôn bị lỗi không được, nên phải dùng cách này
//                    reviewResponse.setIdReview(review.getIdReview());
//                    reviewResponse.setContent(review.getContent());
//                    reviewResponse.setTimestamp(review.getTimestamp());
//                    reviewResponse.setRatingPoint(review.getRatingPoint());
//                    return ResponseEntity.status(HttpStatus.OK).body(reviewResponse);
//                }
//            }
//            return ResponseEntity.notFound().build();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.badRequest().build();
//    }

    private String formatStringByJson(String json) {
        return json.replaceAll("\"", "");
    }
}
