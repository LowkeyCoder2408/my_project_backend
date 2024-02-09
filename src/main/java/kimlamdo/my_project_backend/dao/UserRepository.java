package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.SettingBag;
import kimlamdo.my_project_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}