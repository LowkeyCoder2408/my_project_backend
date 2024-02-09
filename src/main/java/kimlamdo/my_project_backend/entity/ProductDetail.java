package kimlamdo.my_project_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_detail")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String value;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}