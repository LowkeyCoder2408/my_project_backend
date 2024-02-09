package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Order;
import kimlamdo.my_project_backend.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}