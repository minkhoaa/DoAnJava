package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "\"chamcong\"") 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChamCong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"id\"")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "\"employeeid\"") // Khóa ngoại tham chiếu đến bảng NhanVien
    private NhanVien nhanVien;

    @Column(name = "\"ngay\"")
    private LocalDate ngay;

    @Column(name = "\"giovao\"")
    private LocalTime gioVao;

    @Column(name = "\"giora\"")
    private LocalTime gioRa;

    @Column(name = "\"sogiolam\"")
    private Double soGioLam;

    @ManyToMany
    @JoinTable(
        name = "\"loaicong\"",
        joinColumns = @JoinColumn(name = "\"chamcongid\""),
        inverseJoinColumns = @JoinColumn(name = "\"loaicongid\"")
    )
    private Set<BangCong> loaiCong; // Quan hệ nhiều-nhiều với bảng BangCong
}