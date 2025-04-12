package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "\"chamcong\"") // Đảm bảo tên bảng khớp với cơ sở dữ liệu
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChamCong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"id\"") // Tên cột trong cơ sở dữ liệu
    private Long id;

    @Column(name = "\"employeeid\"")
    private Long employeeId;

    @Column(name = "\"ngay\"")
    private LocalDate ngay;

    @Column(name = "\"giovao\"")
    private LocalTime gioVao;

    @Column(name = "\"giora\"")
    private LocalTime gioRa;

    @Column(name = "\"sogiolam\"")
    private Double soGioLam;

    @ManyToMany(mappedBy = "chamCongs") // Quan hệ nhiều-nhiều với BangCong
    private Set<BangCong> bangCongs;
}