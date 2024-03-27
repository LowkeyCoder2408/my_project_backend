package kimlamdo.my_project_backend.service;

import kimlamdo.my_project_backend.entity.Customer;
import kimlamdo.my_project_backend.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {
    public Customer findByEmail(String email);
}
