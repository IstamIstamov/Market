package com.example.simpleProject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = ("bases"))
public class ProductBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("product_base_id"))
    private Integer productBaseId;
    @Column(name = ("prod_name"))
    private String prodName;
    @Column(name = ("received_price"))
    private Double receivedPrice;
    @Column(name = ("selling_price"))
    private Double sellingPrice;
    @Column(name = ("prod_mass"))
    private Double prodMass;
    private LocalDateTime amount;
    @OneToMany(mappedBy = ("productBaseId"), cascade = CascadeType.ALL)
    private Set<Product> products;

}
