package com.example.simpleProject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = ("loaners"))
public class Loaner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("loaner_id"))
    private Integer loanerId;
    @Column(name = ("basked_id"))
    private Integer baskedId;
    @Column(name = ("total_price"))
    private Double totalPrice;
    private Boolean status;
    @OneToMany(mappedBy = ("loanerId"), cascade = CascadeType.ALL)
    private Set<Basket> basket;
    @Column(name = ("create_at"))
    private LocalDateTime createAt;
    @Column(name = ("update_at"))
    private LocalDateTime updateAt;
    @Column(name = ("delete_at"))
    private LocalDateTime deleteAt;
}
