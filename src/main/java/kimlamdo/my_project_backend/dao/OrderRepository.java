package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Order;
import kimlamdo.my_project_backend.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(path = "order")
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Page<Order> findByCustomer_Id(@RequestParam("customerId") int customerId, Pageable pageable);
}