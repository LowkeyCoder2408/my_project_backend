package kimlamdo.my_project_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 256, nullable = false)
    private String name;

    @Column(unique = true, length = 256, nullable = false)
    private String alias;

    @Column(nullable = false, name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;

    @Column(nullable = false, name = "full_description", columnDefinition = "TEXT")
    private String fullDescription;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "updated_time")
    private Date updatedTime;

    private boolean enabled;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "listed_price", nullable = false)
    private int listedPrice;

    @Column(name = "current_price", nullable = false)
    private int currentPrice;

    @Column(name = "discount_percent")
    private int discountPercent;

    private float length;

    private float width;

    private float height;

    private float weight;

    @Column(name = "operating_system")
    private String operatingSystem;

    @Column(name = "main_image", nullable = false)
    private String mainImage;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images = new ArrayList<>();

    private int reviewCount;

    private int ratingCount;

    private float averageRating;
}