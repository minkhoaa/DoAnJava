package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "luong", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Luong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "luongid")
    private Long luongId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeid", referencedColumnName = "manv")
    private NhanVien nhanVien; // Reference to NhanVien entity

    @Column(name = "thang")
    private Integer thang;

    @Column(name = "nam")
    private Integer nam;

    @Column(name = "luongcoban", precision = 10, scale = 2)
    private BigDecimal luongCoBan;

    @Column(name = "phucap", precision = 10, scale = 2)
    private BigDecimal phuCap;

    @Column(name = "baohiem", precision = 10, scale = 2)
    private BigDecimal baoHiem;

    @Column(name = "thunhapthuc", precision = 10, scale = 2)
    private BigDecimal thuNhapThuc;


}