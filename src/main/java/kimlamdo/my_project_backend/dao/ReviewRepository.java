package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Province;
import kimlamdo.my_project_backend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "review")
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}