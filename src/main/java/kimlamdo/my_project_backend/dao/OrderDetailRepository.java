package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Order;
import kimlamdo.my_project_backend.entity.OrderDetail;
import kimlamdo.my_project_backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RepositoryRestResource(path = "order-detail")
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    Page<OrderDetail> findByOrder_Id(@RequestParam("orderId") int orderId, Pageable pageable);

    @Query("SELECT od FROM OrderDetail od WHERE od.order = :order")
    List<OrderDetail> findOrderDetailByOrder(Order order);
}
