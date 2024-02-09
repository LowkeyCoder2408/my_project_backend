package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Order;
import kimlamdo.my_project_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}