package kimlamdo.my_project_backend.service;

import kimlamdo.my_project_backend.dao.UserRepository;
import kimlamdo.my_project_backend.dao.RoleRepository;
import kimlamdo.my_project_backend.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
        return new User(myUser.getEmail(), myUser.getPassword(), rolesToAuthorities(myUser.getRole()));

    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Role role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
    }
}