package com.example.simpleProject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = ("images"))
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("image_id"))
    private Integer imageId;
    private String path;
    private String type;
    private Integer size;
    private String token;
    @Column(name = ("create_at"))
    private LocalDateTime createAt;
    @Column(name = ("update_at"))
    private LocalDateTime updateAt;
    @Column(name = ("delete_at"))
    private LocalDateTime deleteAt;
}
