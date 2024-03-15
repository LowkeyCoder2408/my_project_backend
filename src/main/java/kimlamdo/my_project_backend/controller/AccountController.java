package kimlamdo.my_project_backend.controller;

import kimlamdo.my_project_backend.entity.Customer;
import kimlamdo.my_project_backend.entity.User;
import kimlamdo.my_project_backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController //trả về json
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @CrossOrigin(origins = "http://localhost:3000")
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
}
