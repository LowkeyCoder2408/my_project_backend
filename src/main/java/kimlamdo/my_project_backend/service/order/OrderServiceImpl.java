package kimlamdo.my_project_backend.service.order;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import kimlamdo.my_project_backend.dao.*;
import kimlamdo.my_project_backend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final ObjectMapper objectMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderTrackRepository orderTrackRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;

    public OrderServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(JsonNode jsonData) {
        try {
            Order orderData = objectMapper.treeToValue(jsonData, Order.class);

            int districtId = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("districtId"))));
            Optional<District> district = districtRepository.findById(districtId);
            orderData.setDistrict(district.get().getName());

            orderData.setOrderTime(LocalDateTime.now());

            int provinceId = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("provinceId"))));
            Optional<Province> province = provinceRepository.findById(provinceId);
            orderData.setProvince(province.get().getName());


            int wardId = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("wardId"))));
            Optional<Ward> ward = wardRepository.findById(wardId);
            orderData.setWard(ward.get().getName());

            int customerId = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("customerId"))));
            Optional<Customer> customer = customerRepository.findById(customerId);
            orderData.setCustomer(customer.get());

            int paymentMethod = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("payment"))));
            if (paymentMethod == 2) {
                orderData.setStatus(OrderStatus.PAID);
                orderData.setPaymentMethod(PaymentMethod.VNPay);
            } else {
                orderData.setStatus(OrderStatus.NEW);
                orderData.setPaymentMethod(PaymentMethod.COD);
            }

            Order newOrder = orderRepository.save(orderData);

            JsonNode jsonNode = jsonData.get("product");
            for (JsonNode node : jsonNode) {
                int soldQuantity = Integer.parseInt(formatStringByJson(String.valueOf(node.get("quantity"))));
                Product productResponse = objectMapper.treeToValue(node.get("product"), Product.class);
                Optional<Product> product = productRepository.findById(productResponse.getId());
                product.get().setQuantity(product.get().getQuantity() - soldQuantity);
                product.get().setSoldQuantity(product.get().getSoldQuantity() + soldQuantity);

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product.get());
                orderDetail.setQuantity(soldQuantity);
                orderDetail.setOrder(newOrder);
                orderDetail.setSubtotal(soldQuantity * product.get().getCurrentPrice());
                orderDetailRepository.save(orderDetail);
                productRepository.save(product.get());
            }

            boolean isDefaultAddress = jsonData.get("isDefaultAddress").asBoolean();
            boolean isUseDefaultAddress = jsonData.get("isUseDefaultAddress").asBoolean();

            if (!isUseDefaultAddress) {
                Optional<Address> existingAddress = addressRepository.findByAddressLineAndProvinceAndDistrictAndWardAndCustomer(
                        orderData.getAddressLine(),
                        province.get(),
                        district.get(),
                        ward.get(),
                        customer.get()
                );

                if (existingAddress.isPresent()) {
                    // Đã tồn tại một địa chỉ giống nhau trong cơ sở dữ liệu
                    // Thực hiện các hành động cần thiết, ví dụ: cập nhật thông tin
                    if (isDefaultAddress == true) {
                        addressRepository.updateIsDefaultAddress();
                    }
                    Address existing = existingAddress.get();
                    existing.setDefaultAddress(isDefaultAddress);
                    // Cập nhật thông tin khác nếu cần
                    addressRepository.save(existing);
                } else {
                    Address addressData = new Address();
                    addressData.setAddressLine(orderData.getAddressLine());
                    if (isDefaultAddress == true) {
                        addressRepository.updateIsDefaultAddress();
                    }
                    addressData.setDefaultAddress(isDefaultAddress);
                    addressData.setProvince(province.get());
                    addressData.setDistrict(district.get());
                    addressData.setWard(ward.get());
                    addressData.setCustomer(customer.get());
                    addressRepository.save(addressData);
                }
            }

            OrderTrack orderTrack = new OrderTrack();
            orderTrack.setNotes(orderData.getNote());
            orderTrack.setOrder(orderData);
            orderTrack.setStatus(orderData.getStatus());
            orderTrack.setUpdatedTime(orderData.getOrderTime());
            orderTrackRepository.save(orderTrack);

            cartItemRepository.deleteAllCartItemsByCustomerId(customer.get().getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(JsonNode jsonData) {
//        try {
//            int idOrder = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("idOrder"))));
//            String status = formatStringByJson(String.valueOf(jsonData.get("status")));
//            Optional<Order> order = orderRepository.findById(idOrder);
//            order.get().setStatus(status);
//
//            // Lấy ra order detail
//            if (status.equals("Bị huỷ")) {
//                List<OrderDetail> orderDetailList = orderDetailRepository.findOrderDetailsByOrder(order.get());
//                for (OrderDetail orderDetail : orderDetailList) {
//                    Book bookOrderDetail = orderDetail.getBook();
//                    bookOrderDetail.setSoldQuantity(bookOrderDetail.getSoldQuantity() - orderDetail.getQuantity());
//                    bookOrderDetail.setQuantity(bookOrderDetail.getQuantity() + orderDetail.getQuantity());
//                    bookRepository.save(bookOrderDetail);
//                }
//            }
//
//            orderRepository.save(order.get());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().build();
//        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> cancel(JsonNode jsonData) {
//        try {
//            int idUser = Integer.parseInt(formatStringByJson(String.valueOf(jsonData.get("idUser"))));
//            User user = userRepository.findById(idUser).get();
//
//            Order order = orderRepository.findFirstByUserOrderByIdOrderDesc(user);
//            order.setStatus("Bị huỷ");
//
//            List<OrderDetail> orderDetailList = orderDetailRepository.findOrderDetailsByOrder(order);
//            for (OrderDetail orderDetail : orderDetailList) {
//                Book bookOrderDetail = orderDetail.getBook();
//                bookOrderDetail.setSoldQuantity(bookOrderDetail.getSoldQuantity() - orderDetail.getQuantity());
//                bookOrderDetail.setQuantity(bookOrderDetail.getQuantity() + orderDetail.getQuantity());
//                bookRepository.save(bookOrderDetail);
//            }
//
//            orderRepository.save(order);
//
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//
        return ResponseEntity.ok().build();
    }

    private String formatStringByJson(String json) {
        return json.replaceAll("\"", "");
    }
}
