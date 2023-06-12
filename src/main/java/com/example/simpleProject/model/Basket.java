package com.example.simpleProject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = ("baskets"))
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("basket_id"))
    private Integer basketId;
    @OneToMany(mappedBy = "basketId", cascade = CascadeType.ALL)
    private Set<Product> products;
    @Column(name = ("prod_mass"))
    private Double prodMass;
    @Column(name = ("prod_price"))
    private Double prodPrice;
    @Column(name = ("total_price"))
    private Double totalPrice;
    @Column(name = ("loaner_id"))
    private Integer loanerId;
    /*@ManyToOne
    @JoinColumn(name = ("loaner_id"), insertable = false, updatable = false)
    private Loaner loaner;*/
    @Column(name = ("create_at"))
    private LocalDateTime createAt;
    @Column(name = ("update_at"))
    private LocalDateTime updateAt;
    @Column(name = ("delete_at"))
    private LocalDateTime deleteAt;
}
