package kimlamdo.my_project_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128, nullable = false, unique = true)
    private String email;

    @Column(length = 64, nullable = false)
    private String password;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @Column(name = "phone_number", length = 12, nullable = false)
    private String phoneNumber;

    @Column(length = 100)
    private String photos;

    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}