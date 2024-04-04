package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Order;
import kimlamdo.my_project_backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(path = "product")
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Tìm kiếm theo 1 yếu tố
    Page<Product> findByNameContaining(@RequestParam("productName") String productName, Pageable pageable);

    Product findByAlias(@RequestParam("productAlias") String productAlias);

    Page<Product> findByCategory_Id(@RequestParam("categoryId") int categoryId, Pageable pageable);

    Page<Product> findByBrand_Id(@RequestParam("brandId") int brandId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.currentPrice >= :minPrice AND p.currentPrice <= :maxPrice")
    Page<Product> findByCurrentPriceBetween(@RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice, Pageable pageable);

    // Tìm kiếm theo 2 yếu tố
    Page<Product> findByNameContainingAndCategory_Id(@RequestParam("productName") String productName, @RequestParam("categoryId") int categoryId, Pageable pageable);

    Page<Product> findByNameContainingAndCurrentPriceBetween(@RequestParam("productName") String productName, @RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice, Pageable pageable);

    Page<Product> findByCategory_IdAndCurrentPriceBetween(@RequestParam("categoryId") int categoryId, @RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice, Pageable pageable);

    // Tìm kiếm theo 3 yếu tố
    Page<Product> findByNameContainingAndCategory_IdAndCurrentPriceBetween(@RequestParam("productName") String productName, @RequestParam("categoryId") int categoryId, @RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice, Pageable pageable);
}