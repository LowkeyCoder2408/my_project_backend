package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Role;
import kimlamdo.my_project_backend.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "setting")
public interface SettingRepository extends JpaRepository<Setting, String> {

}