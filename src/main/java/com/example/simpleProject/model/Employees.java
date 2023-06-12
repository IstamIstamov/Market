package com.example.simpleProject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
@Data
@Entity
@Table(name = ("employees"))
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("employee_id"))
    private Integer employeeId;
    @OneToMany(mappedBy = "employeeId", cascade = CascadeType.ALL)
    private Set<User> users;
    @Column(name = ("create_at"))
    private LocalDateTime createAt;
    @Column(name = ("update_at"))
    private LocalDateTime updateAt;
    @Column(name = ("delete_at"))
    private LocalDateTime deleteAt;

}
