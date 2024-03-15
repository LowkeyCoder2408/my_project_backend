package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Customer;
import kimlamdo.my_project_backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "customer")
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);

    public Customer findByEmail(String email);
}