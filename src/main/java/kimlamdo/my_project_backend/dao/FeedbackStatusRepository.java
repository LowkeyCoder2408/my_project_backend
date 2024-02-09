package kimlamdo.my_project_backend.dao;

import kimlamdo.my_project_backend.entity.Feedback;
import kimlamdo.my_project_backend.entity.FeedbackStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackStatusRepository extends JpaRepository<FeedbackStatus, Integer> {

}