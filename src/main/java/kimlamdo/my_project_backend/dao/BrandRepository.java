package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Brand;
import kimlamdo.my_project_backend.entity.Category;
import kimlamdo.my_project_backend.entity.Order;
import kimlamdo.my_project_backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RepositoryRestResource(path = "brand")
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    @Query("SELECT b FROM Brand b WHERE b.id = :brandId")
    Brand findById(@RequestParam("brandId") int brandId);
}