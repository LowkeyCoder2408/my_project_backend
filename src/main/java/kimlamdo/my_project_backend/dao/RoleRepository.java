package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Review;
import kimlamdo.my_project_backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}