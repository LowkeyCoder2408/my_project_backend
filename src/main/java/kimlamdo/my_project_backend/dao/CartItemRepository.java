package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Brand;
import kimlamdo.my_project_backend.entity.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(path = "cart-item")
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Page<CartItem> findByCustomer_Id(@RequestParam("customerId") int customerId, Pageable pageable);
}