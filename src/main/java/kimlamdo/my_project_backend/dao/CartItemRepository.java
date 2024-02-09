package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Brand;
import kimlamdo.my_project_backend.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}