package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Setting;
import kimlamdo.my_project_backend.entity.SettingBag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingBagRepository extends JpaRepository<SettingBag, Integer> {

}