package kimlamdo.my_project_backend.service.customer;

import kimlamdo.my_project_backend.entity.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerSecurityService extends UserDetailsService {
    public Customer findByEmail(String email);
}
