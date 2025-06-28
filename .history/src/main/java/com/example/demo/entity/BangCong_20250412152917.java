package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "\"bangcong\"") // Đảm bảo tên bảng khớp với cơ sở dữ liệu
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BangCong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"mabc\"") // Tên cột trong cơ sở dữ liệu
    private Long mabc;

    @Column(name = "\"nam\"")
    private Integer nam;

    @Column(name = "\"thang\"")
    private Integer thang;

    @Column(name = "\"ngay\"")
    private LocalDate ngay;

    @Column(name = "\"giovao\"")
    private LocalTime gioVao;

    @Column(name = "\"giora\"")
    private LocalTime gioRa;

    @Column(name = "\"phutvao\"")
    private Integer phutVao;

    @Column(name = "\"phutra\"")
    private Integer phutRa;

    @ManyToOne
    @JoinColumn(name = "\"manv\"") 
    private NhanVien nhanVien;

    @ManyToMany
    @JoinTable(
        name = "\"loaicong\"",
        joinColumns = @JoinColumn(name = "\"mabc\""), // Cột tham chiếu đến BangCong
        inverseJoinColumns = @JoinColumn(name = "\"machamcong\"") // Cột tham chiếu đến ChamCong
    )
    private Set<ChamCong> chamCongs;
}