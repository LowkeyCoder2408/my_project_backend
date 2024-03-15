package kimlamdo.my_project_backend.service;

import kimlamdo.my_project_backend.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findByEmail(String email);
}
