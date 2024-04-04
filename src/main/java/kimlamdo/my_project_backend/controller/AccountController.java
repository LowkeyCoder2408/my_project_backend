package kimlamdo.my_project_backend.controller;

import kimlamdo.my_project_backend.entity.Customer;
import kimlamdo.my_project_backend.security.JwtResponse;
import kimlamdo.my_project_backend.security.LoginRequest;
import kimlamdo.my_project_backend.service.account.AccountService;
import kimlamdo.my_project_backend.service.customer.CustomerSecurityService;
import kimlamdo.my_project_backend.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController //trả về json
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerSecurityService customerService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@Validated @RequestBody Customer customer) {
        // write dto
        ResponseEntity<?> response = accountService.registerUser(customer);
        return response;
    }

    @GetMapping("/enable")
    public ResponseEntity<?> enableAccount(@RequestParam String email, @RequestParam String verificationCode) {
        // write dto
        ResponseEntity<?> response = accountService.enableAccount(email, verificationCode);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            // Nếu xác thực thành công, tạo token JWT
            if (authentication.isAuthenticated()) {
                final String jwt = jwtService.generateToken(loginRequest.getEmail());
                return ResponseEntity.ok(new JwtResponse(jwt));
            }
        } catch (AuthenticationException e) {
            // Xác thực không thành công, trả về lỗi hoặc thông báo
            return ResponseEntity.badRequest().body("Email hặc mật khẩu không chính xác.");
        }
        return ResponseEntity.badRequest().body("Xác thực không thành công.");
    }
}
