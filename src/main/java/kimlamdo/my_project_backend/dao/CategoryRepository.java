package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Address;
import kimlamdo.my_project_backend.entity.Brand;
import kimlamdo.my_project_backend.entity.Category;
import kimlamdo.my_project_backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(path = "category")
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findById(@RequestParam("categoryId") int categoryId);

    @Query("SELECT c.brands FROM Category c WHERE c.id = :categoryId")
    Page<Brand> findBrandsById(@RequestParam("categoryId") int categoryId, Pageable pageable);

}