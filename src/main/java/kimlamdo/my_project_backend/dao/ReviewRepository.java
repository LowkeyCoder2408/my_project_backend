package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "review")
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Page<Review> findByProduct_Id(int productId, Pageable pageable);
}
