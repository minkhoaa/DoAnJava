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
@Table(name = "\"nhanvien\"") 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"manv\"") 
    private Long manv;

    @Column(name = "\"hoten\"") 
    private String hoten;

    @Column(name = "\"gioitinh\"") 
    private String gioitinh;

    @Column(name = "\"ngaysinh\"") 
    private LocalDate ngaysinh;

    @Column(name = "\"dienthoai\"") 
    private String dienthoai;

    @Column(name = "\"cccd\"")
    private String cccd;

    @Column(name = "\"diachi\"")
    private String diachi;

    @Column(name = "\"hinhanh\"") 
    private String hinhanh;

    @Column(name = "\"idpb\"") 
    private Long idpb;

    @Column(name = "\"idcv\"") 
    private Long idcv;

    @Column(name = "\"idtd\"") 
    private Long idtd;
}
