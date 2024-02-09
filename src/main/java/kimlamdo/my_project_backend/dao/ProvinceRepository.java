package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Product;
import kimlamdo.my_project_backend.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

}