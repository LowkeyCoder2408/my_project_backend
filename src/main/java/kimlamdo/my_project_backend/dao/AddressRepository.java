package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Address;
import kimlamdo.my_project_backend.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(path = "address")
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Page<Address> findByCustomer_Id(@RequestParam("customerId") Integer customerId, Pageable pageable);
}