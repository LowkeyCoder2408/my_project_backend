package kimlamdo.my_project_backend.service.customer;

import kimlamdo.my_project_backend.dao.CustomerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomerSecurityServiceImpl implements CustomerSecurityService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerSecurityServiceImpl(CustomerRepository customerRepository) {
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