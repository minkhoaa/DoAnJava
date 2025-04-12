package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "\"loaicong\"") // Đảm bảo tên bảng khớp với cơ sở dữ liệu
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoaiCong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"idlc\"") // Tên cột trong cơ sở dữ liệu
    private Long idlc;

    @Column(name = "\"tenlc\"")
    private String tenLc;

    @Column(name = "\"heso\"")
    private Double heSo;
}