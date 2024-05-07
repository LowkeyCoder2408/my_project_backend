package kimlamdo.my_project_backend.dao;

import jakarta.transaction.Transactional;
import kimlamdo.my_project_backend.entity.Address;
import kimlamdo.my_project_backend.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RepositoryRestResource(path = "address")
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Page<Address> findByCustomer_Id(@RequestParam("customerId") Integer customerId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Address a SET a.isDefaultAddress = false")
    public void updateIsDefaultAddress();

    Page<Address> findByIsDefaultAddressTrueAndCustomer_id(@RequestParam("customerId") Integer customerId, Pageable pageable);
}