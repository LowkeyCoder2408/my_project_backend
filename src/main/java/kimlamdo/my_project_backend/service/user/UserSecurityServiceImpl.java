package kimlamdo.my_project_backend.service.user;

import kimlamdo.my_project_backend.dao.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserSecurityServiceImpl implements UserSecurityService {
    private UserRepository userRepository;

    @Autowired
    public UserSecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public kimlamdo.my_project_backend.entity.User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        kimlamdo.my_project_backend.entity.User myUser = findByEmail(email);
        if (myUser == null) {
            throw new UsernameNotFoundException("Tài khoản không tồn tại");
        }

        // Tạo một danh sách quyền
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(myUser.getRole().getName())); // Chuyển đổi Role thành chuỗi

        // Tạo đối tượng UserDetails với thông tin người dùng và quyền
        UserDetails userDetails = new User(myUser.getEmail(), myUser.getPassword(), authorities);
        return userDetails;
    }
}