
package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Product;
import kimlamdo.my_project_backend.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "product-image")
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    List<ProductImage> findByOrderById();

    void deleteByProduct(Product product);
}