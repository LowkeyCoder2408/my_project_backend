package kimlamdo.my_project_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "setting")
public class Setting {
    @Id
    @Column(name = "`key`", nullable = false, length = 128)
    private String key;

    @Column(nullable = false, length = 10024)
    private String value;

    @Enumerated(EnumType.STRING)
    @Column(length = 45, nullable = false)
    private SettingCategory category;
}