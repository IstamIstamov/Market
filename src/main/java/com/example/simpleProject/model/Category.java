package com.example.simpleProject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = ("categories"))
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("category_id"))
    private Integer categoryId;
    @Column(name = ("category_name"))
    private String categoryName;
    @OneToMany(mappedBy = ("categoryId"), cascade = CascadeType.ALL)
    private Set<Product> products;
    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = ("reports_id"), insertable=false, updatable=false)
    private Reports reports;*/
    @Column(name = ("reports_id"))
    private Integer reportsId;
    @Column(name = ("create_at"))
    private LocalDateTime createAt;
    @Column(name = ("update_at"))
    private LocalDateTime updateAt;
    @Column(name = ("delete_at"))
    private LocalDateTime deleteAt;
}
