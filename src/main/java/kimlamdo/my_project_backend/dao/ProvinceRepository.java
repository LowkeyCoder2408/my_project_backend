package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Product;
import kimlamdo.my_project_backend.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "province")
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

}