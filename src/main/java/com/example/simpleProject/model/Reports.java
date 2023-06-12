package com.example.simpleProject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = ("reports"))
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("reports_id"))
    private Integer reportsId;
    @Column(name = ("prod_name"))
    private String prodName;
    @Column(name = ("prod_percent"))
    private Double prodPercent;
    @OneToMany(mappedBy = "reportsId", cascade = CascadeType.ALL)
    private Set<Category> categories;
    @Column(name = ("create_at"))
    private LocalDateTime createAt;
    @Column(name = ("update_at"))
    private LocalDateTime updateAt;
    @Column(name = ("delete_at"))
    private LocalDateTime deleteAt;
}
