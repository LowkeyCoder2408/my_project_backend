package kimlamdo.my_project_backend.service.cartItem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kimlamdo.my_project_backend.dao.CartItemRepository;
import kimlamdo.my_project_backend.dao.CustomerRepository;
import kimlamdo.my_project_backend.dao.ProductRepository;
import kimlamdo.my_project_backend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final ObjectMapper objectMapper;
    @Autowired
    public CustomerRepository customerRepository;
    @Autowired
    public CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public CartItemServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseEntity<?> save(JsonNode jsonData) {
        try {
            // Chuyển đổi một đối tượng JSON thành một đối tượng Java dựa trên mô hình của lớp CartItem
            CartItem cartItem = objectMapper.treeToValue(jsonData, CartItem.class);

            int customerId = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("customerId"))));
            Optional<Customer> customerOptional = customerRepository.findById(customerId);

            // Danh sách item của user
            List<CartItem> cartItemDataList = new ArrayList<>();
            Page<CartItem> cartItemOfCustomerListPage = cartItemRepository.findByCustomer_Id(customerId, Pageable.unpaged());
            List<CartItem> cartItemOfCustomerList = cartItemOfCustomerListPage.getContent();
            // Lưu khách hàng của giỏ hàng
            customerOptional.ifPresent(cartItem::setCustomer);

            boolean isHad = false;
            for (CartItem cartItemOfCustomer : cartItemOfCustomerList) {
                // Nếu trong cart của customer có item đó rồi thì sẽ update lại quantity
                if (cartItemOfCustomer.getProduct().getId() == cartItem.getProduct().getId()) {
                    cartItemOfCustomer.setQuantity(cartItemOfCustomer.getQuantity() + cartItem.getQuantity());
                    isHad = true;
                    cartItemRepository.save(cartItemOfCustomer);
                    return ResponseEntity.ok().body(cartItemOfCustomer.getId());
//                    break;
                }
            }

            // Nếu chưa có thì thêm mới item đó
            if (!isHad) {
                CartItem cartItemOfCustomer = new CartItem();
                cartItemOfCustomer.setCustomer(customerOptional.get());
                cartItemOfCustomer.setQuantity(cartItem.getQuantity());
                cartItemOfCustomer.setProduct(cartItem.getProduct());
//                cartItemOfCustomerList.add(cartItemOfCustomer);
                cartItemRepository.save(cartItemOfCustomer);
                return ResponseEntity.ok().body(cartItemOfCustomer.getId());
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<?> update(JsonNode jsonData) {
        try {
            int cartId = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("id"))));
            int quantity = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("quantity"))));
            Optional<CartItem> cartItem = cartItemRepository.findById(cartId);
            cartItem.get().setQuantity(quantity);
            cartItemRepository.save(cartItem.get());
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