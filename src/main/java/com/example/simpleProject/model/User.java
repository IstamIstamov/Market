package com.example.simpleProject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = ("users"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("user_id"))
    private Integer userId;
    @Column(name = ("first_name"))
    private String firstName;
    @Column(name = ("last_name"))
    private String lastName;
    @Column(name = ("middle_name"))
    private String middleName;
    private String username;
    @Column(name = ("borrow_name"))
    private String borrowName;
    @Column(name = ("phone_number"))
    private String phoneNumber;
    @Column(name = ("passport_series"))
    private String passportSeries;
    @Column(name = ("first_address"))
    private String firstAddress;
    @Column(name = ("second_address"))
    private String secondAddress;
    @Column(name = ("monthly_price"))
    private Double monthlyPrice;
    @Column(name = ("employee_id"))
    private Integer employeeId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = ("loaner_id"), referencedColumnName = ("loaner_id"), insertable = false,updatable = false)
    private Loaner loaner;
    @Column(name = ("loaner_id"))
    private Integer loanerId;
    @Column(name = ("birth_date"))
    private LocalDateTime birthDate;
    @Column(name = ("working_time"))
    private LocalDateTime workingTime;
    @Column(name = ("working_days"))
    private LocalDateTime workingDays;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = ("image_id"), referencedColumnName = ("image_id"),insertable = false,updatable = false)
    private Image image;
    @Column(name = ("image_id"))
    private Integer imageId;
    @Column(name = ("create_at"))
    private LocalDateTime createAt;
    @Column(name = ("update_at"))
    private LocalDateTime updateAt;
    @Column(name = ("delete_at"))
    private LocalDateTime deleteAt;
}
