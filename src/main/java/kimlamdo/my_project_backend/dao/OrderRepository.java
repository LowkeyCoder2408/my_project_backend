package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Customer;
import kimlamdo.my_project_backend.entity.Order;
import kimlamdo.my_project_backend.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RepositoryRestResource(path = "order")
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Page<Order> findByCustomer_Id(@RequestParam("customerId") int customerId, Pageable pageable);

    @Query("SELECT SUM(o.total) FROM Order o WHERE o.customer.id = ?1")
    int calculateTotalAmountByCustomerId(Integer customerId);

    @Query("SELECT DISTINCT o.customer FROM Order o")
    Page<Customer> findDistinctCustomers(Pageable pageable);
}