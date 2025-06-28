package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
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
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "\"idcv\"", referencedColumnName = "\"idcv\"", nullable = true)
    private ChucVu chucVu;

    @ManyToOne
    @JoinColumn(name = "\"idpb\"", referencedColumnName = "\"idpb\"", nullable = true)
    private PhongBan phongBan;

    @ManyToOne
    @JoinColumn(name = "\"idtd\"", referencedColumnName = "\"idtd\"", nullable = true)
    private TrinhDo trinhDo;
}
