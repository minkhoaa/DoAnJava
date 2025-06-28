package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"chucvu\"") 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChucVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"idcv\"")
    private Long id;

    @Column(name = "\"tencv\"") 
    private String name;
}