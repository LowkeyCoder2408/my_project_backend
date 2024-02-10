
package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.ProductDetail;
import kimlamdo.my_project_backend.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "product-image")
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

}