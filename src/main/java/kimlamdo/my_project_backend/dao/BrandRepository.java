package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Brand;
import kimlamdo.my_project_backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "brand")
public interface BrandRepository extends JpaRepository<Brand, Integer> {

}