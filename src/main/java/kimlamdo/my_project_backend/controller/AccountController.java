package kimlamdo.my_project_backend.controller;

import kimlamdo.my_project_backend.entity.Customer;
import kimlamdo.my_project_backend.entity.User;
import kimlamdo.my_project_backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //trả về json
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@Validated @RequestBody Customer customer) {
        ResponseEntity<?> response = accountService.registerUser(customer);
        return response;
    }
}
