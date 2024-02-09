package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Role;
import kimlamdo.my_project_backend.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Integer> {

}