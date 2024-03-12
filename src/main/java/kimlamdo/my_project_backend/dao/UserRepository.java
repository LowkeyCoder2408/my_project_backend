package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.SettingBag;
import kimlamdo.my_project_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "user")
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
}