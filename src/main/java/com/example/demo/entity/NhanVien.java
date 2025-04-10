package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"nhanvien\"") // Ánh xạ bảng "nhanvien"
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"manv\"") // Ánh xạ cột "manv"
    private Long manv;

    @Column(name = "\"hoten\"") // Ánh xạ cột "hoten"
    private String hoten;

    @Column(name = "\"gioitinh\"") // Ánh xạ cột "gioitinh"
    private String gioitinh;

    @Column(name = "\"ngaysinh\"") // Ánh xạ cột "ngaysinh"
    private LocalDate ngaysinh;

    @Column(name = "\"dienthoai\"") // Ánh xạ cột "dienthoai"
    private String dienthoai;

    @Column(name = "\"cccd\"") // Ánh xạ cột "cccd"
    private String cccd;

    @Column(name = "\"diachi\"") // Ánh xạ cột "diachi"
    private String diachi;

    @Column(name = "\"hinhanh\"") // Ánh xạ cột "hinhanh"
    private String hinhanh;

    @Column(name = "\"idpb\"") // Ánh xạ cột "idpb"
    private Long idpb;

    @Column(name = "\"idcv\"") // Ánh xạ cột "idcv"
    private Long idcv;

    @Column(name = "\"idtd\"") // Ánh xạ cột "idtd"
    private Long idtd;
}
