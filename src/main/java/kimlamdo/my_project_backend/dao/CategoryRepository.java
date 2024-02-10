package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Address;
import kimlamdo.my_project_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "category")
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}