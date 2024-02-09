package kimlamdo.my_project_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    @Column(nullable = false, length = 64)
    private String name;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<Ward> wards;
}