package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Product;
import kimlamdo.my_project_backend.entity.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(path = "province")
public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    // Tìm kiếm theo 1 yếu tố
//    Page<Province> findByNameContaining(@RequestParam("provinceName") String provinceName, Pageable pageable);
}