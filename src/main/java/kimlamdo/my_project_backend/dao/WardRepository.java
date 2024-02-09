package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.User;
import kimlamdo.my_project_backend.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer> {

}