package kimlamdo.my_project_backend.service;

import kimlamdo.my_project_backend.dao.CustomerRepository;
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
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public kimlamdo.my_project_backend.entity.Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        kimlamdo.my_project_backend.entity.Customer myCustomer = findByEmail(email);
        if (myCustomer == null) {
            throw new UsernameNotFoundException("Tài khoản không tồn tại");
        }
        // Trả về một UserDetails với thông tin khách hàng và danh sách quyền trống
        return new User(myCustomer.getEmail(), myCustomer.getPassword(), Collections.emptyList());
    }
}