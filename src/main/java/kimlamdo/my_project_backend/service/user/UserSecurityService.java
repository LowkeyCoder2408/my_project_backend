package kimlamdo.my_project_backend.service.user;

import kimlamdo.my_project_backend.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserSecurityService  extends UserDetailsService {
    public User findByEmail(String email);

//    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
