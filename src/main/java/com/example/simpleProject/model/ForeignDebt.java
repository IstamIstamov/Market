package com.example.simpleProject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = ("foreigns"))
public class ForeignDebt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("foreign_id"))
    private Integer foreignId;
    @Column(name = ("company_name"))
    private String companyName;
    @Column(name = ("full_name"))
    private String fullName;
    @Column(name = ("first_phone_number"))
    private String firstPhoneNumber;
    @Column(name = ("second_phone_number"))
    private String secondPhoneNumber;
    @OneToMany(mappedBy = ("foreignId"), cascade = CascadeType.ALL)
    private Set<Product> products;
    private Boolean status;
    @Column(name = ("create_at"))
    private LocalDateTime createAt;
    @Column(name = ("update_at"))
    private LocalDateTime updateAt;
    @Column(name = ("delete_at"))
    private LocalDateTime deleteAt;
}
