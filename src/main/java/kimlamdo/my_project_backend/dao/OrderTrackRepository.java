package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Order;
import kimlamdo.my_project_backend.entity.OrderTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTrackRepository extends JpaRepository<OrderTrack, Integer> {

}