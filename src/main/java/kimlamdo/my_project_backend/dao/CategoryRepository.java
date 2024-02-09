package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Address;
import kimlamdo.my_project_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}