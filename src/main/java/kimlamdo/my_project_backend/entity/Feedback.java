package kimlamdo.my_project_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", nullable = false, length = 45)
    private String fullName;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(nullable = false, name = "message", columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    private FeedbackStatus feedBackStatus;

    private Date sentTime;
}