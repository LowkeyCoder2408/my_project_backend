package kimlamdo.my_project_backend.service;

import kimlamdo.my_project_backend.dao.CustomerRepository;
import kimlamdo.my_project_backend.dao.UserRepository;
import kimlamdo.my_project_backend.entity.Customer;
import kimlamdo.my_project_backend.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    public ResponseEntity<?> registerUser(Customer customer) {
        // Kiểm tra email
        if (customerRepository.existsByEmail(customer.getEmail())) {
            return ResponseEntity.badRequest().body(new Notification("Email đã tồn tại!"));
        }

        // Mã hóa mật khẩu
        String encryptPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encryptPassword);

        // Gán và gửi thông tin kích hoạt
        customer.setVerificationCode(createVerificationCode());
        customer.setEnabled(false);

        // Lưu người dùng vào DB
        Customer registeredCustomer = customerRepository.save(customer);

        // Gửi email kích hoạt cho người dùng
        sendVerificationEmail(customer.getFullName(), customer.getEmail(), customer.getVerificationCode());

        return ResponseEntity.ok("Đăng ký người dùng thành công!");
    }

    private String createVerificationCode() {
        return UUID.randomUUID().toString();
    }

    private void sendVerificationEmail(String customerName, String email, String verificationCode) {
        String url = "http://localhost:3000/enable/" + email + "/" + verificationCode;
        String subject = "[TECH HUB] KÍCH HOẠT TÀI KHOẢN NGƯỜI DÙNG";
        String text = "<body>\n" +
                "    <p>Xin chào " + customerName + "</p>\n" +
                "\n" +
                "    <p>Chào mừng bạn đã đăng ký tài khoản với ứng dụng của chúng tôi, Tech Hub. Để hoàn tất quá trình đăng ký và bắt đầu sử dụng, bạn cần kích hoạt tài khoản của mình.</p>\n" +
                "\n" +
                "<p>Vui lòng nhấp <a href=\"" + url + "\">tại đây</a> để kích hoạt tài khoản của bạn.</p>\n" +
                "    <p>Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email này hoặc liên hệ với chúng tôi ngay lập tức.</p>\n" +
                "\n" +
                "    <p>Xin chân thành cảm ơn,<br>\n" +
                "    Đỗ Kim Lâm - Tech Hub's Co-founder</p>\n" +
                "</body>\n" +
                "</html>";
        emailService.sendEmail("dokimlamut@gmail.com", email, subject, text);
    }


    public ResponseEntity<?> enableAccount(String email, String verificationCode) {
        // TÌm theo email
        Customer customer = customerRepository.findByEmail(email);

        if (customer == null) {
            return ResponseEntity.badRequest().body(new Notification("Người dùng không tồn tại!"));
        }

        if (customer.isEnabled()) {
            return ResponseEntity.badRequest().body(new Notification("Tài khoản đã được kích hoạt!"));
        }

        if (verificationCode.equals(customer.getVerificationCode())) {
            customer.setEnabled(true);
            customerRepository.save(customer);
            return ResponseEntity.ok("Kích hoạt tài khoản thành công!");
        } else {
            return ResponseEntity.badRequest().body(new Notification("Mã kích hoạt không chính xác!"));
        }
    }
}