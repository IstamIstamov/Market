package com.example.simpleProject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = ("products"))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("product_id"))
    private Integer productId;
    @Column(name = ("product_name"))
    private String productName;
    @Column(name = ("received_price"))
    private Double receivedPrice;
    @Column(name = ("selling_price"))
    private Double sellingPrice;
    @Column(name = ("product_mass"))
    private Double productMass;
    private Double amount;
    @Column(name = ("expired_at"))
    private LocalDateTime expiredAt;
    @Column(name = ("basket_id"))
    private Integer basketId;
    @Column(name = ("category_id"))
    private Integer categoryId;
    @Column(name = ("product_base_id"))
    private Integer productBaseId;
    @Column(name = ("foreign_id"))
    private Integer foreignId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = ("image_id"), referencedColumnName = ("image_id"), insertable = false,updatable = false)
    private Image image;
    @Column(name = ("create_at"))
    private LocalDateTime createAt;
    @Column(name = ("update_at"))
    private LocalDateTime updateAt;
    @Column(name = ("delete_at"))
    private LocalDateTime deleteAt;
}
