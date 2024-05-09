package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Customer;
import kimlamdo.my_project_backend.entity.FavoriteProduct;
import kimlamdo.my_project_backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RepositoryRestResource(path = "favorite-product")
public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Integer> {
    public FavoriteProduct findFavoriteProductByProductAndCustomer(Product product, Customer customer);

    public List<FavoriteProduct> findFavoriteProductsByCustomer(Customer customer);

    Page<FavoriteProduct> findByCustomer_Id(@RequestParam("customerId") int customerId, Pageable pageable);
}