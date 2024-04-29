package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(path = "review")
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Page<Review> findByProduct_Id(int productId, Pageable pageable);

    Page<Review> findByCustomer_Id(@RequestParam("customerId") int customerId, Pageable pageable);
}
