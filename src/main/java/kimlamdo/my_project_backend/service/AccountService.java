package kimlamdo.my_project_backend.service;

import kimlamdo.my_project_backend.dao.CustomerRepository;
import kimlamdo.my_project_backend.entity.Customer;
import kimlamdo.my_project_backend.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<?> registerUser(Customer customer) {
        // Kiểm tra email
        if (customerRepository.existsByEmail(customer.getEmail())) {
            return ResponseEntity.badRequest().body(new Notification("Email đã tồn tại!"));
        }
        // Lưu người dùng vào DB
        Customer registeredCustomer = customerRepository.save(customer);
        return ResponseEntity.ok("Đăng ký người dùng thành công!");
    }
}
