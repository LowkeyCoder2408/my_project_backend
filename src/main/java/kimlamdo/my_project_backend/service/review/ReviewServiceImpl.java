package kimlamdo.my_project_backend.service.review;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
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

    public ReviewServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(JsonNode jsonData) {
        try {
            Review reviewData = objectMapper.treeToValue(jsonData, Review.class);

//            int districtId = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("districtId"))));
//            Optional<District> district = districtRepository.findById(districtId);
//            orderData.setDistrict(district.get().getName());
//
//            orderData.setOrderTime(LocalDateTime.now());
//
//            int provinceId = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("provinceId"))));
//            Optional<Province> province = provinceRepository.findById(provinceId);
//            orderData.setProvince(province.get().getName());
//
//
//            int wardId = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("wardId"))));
//            Optional<Ward> ward = wardRepository.findById(wardId);
//            orderData.setWard(ward.get().getName());
//
//            int customerId = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("customerId"))));
//            Optional<Customer> customer = customerRepository.findById(customerId);
//            orderData.setCustomer(customer.get());
//
//            int paymentMethod = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("payment"))));
//            if (paymentMethod == 2) {
//                orderData.setStatus(OrderStatus.PAID);
//                orderData.setPaymentMethod(PaymentMethod.VNPay);
//            } else {
//                orderData.setStatus(OrderStatus.NEW);
//                orderData.setPaymentMethod(PaymentMethod.COD);
//            }
//
//            Order newOrder = orderRepository.save(orderData);
//
//            JsonNode jsonNode = jsonData.get("product");
//            for (JsonNode node : jsonNode) {
//                int soldQuantity = Integer.parseInt(formatStringByJson(String.valueOf(node.get("quantity"))));
//                Product productResponse = objectMapper.treeToValue(node.get("product"), Product.class);
//                Optional<Product> product = productRepository.findById(productResponse.getId());
//                product.get().setQuantity(product.get().getQuantity() - soldQuantity);
//                product.get().setSoldQuantity(soldQuantity);
//
//                OrderDetail orderDetail = new OrderDetail();
//                orderDetail.setProduct(product.get());
//                orderDetail.setQuantity(soldQuantity);
//                orderDetail.setOrder(newOrder);
//                orderDetail.setSubtotal(soldQuantity * product.get().getCurrentPrice());
//                orderDetailRepository.save(orderDetail);
//                productRepository.save(product.get());
//            }
//
//            OrderTrack orderTrack = new OrderTrack();
//            orderTrack.setNotes(orderData.getNote());
//            orderTrack.setOrder(orderData);
//            orderTrack.setStatus(orderData.getStatus());
//            orderTrack.setUpdatedTime(orderData.getOrderTime());
//            orderTrackRepository.save(orderTrack);
//
//            cartItemRepository.deleteAllCartItemsByCustomerId(customer.get().getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> update(JsonNode jsonData) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(JsonNode jsonData) {
        return null;
    }
}
