//package kimlamdo.my_project_backend.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.util.*;
//
//@Data
//@Entity
//@Table(name = "product_color")
//public class ProductColor {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false)
//    private String colorCode;
//
//    @ManyToMany(cascade = {
//            CascadeType.PERSIST, CascadeType.MERGE,
//            CascadeType.DETACH, CascadeType.REFRESH
//    })
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//}
