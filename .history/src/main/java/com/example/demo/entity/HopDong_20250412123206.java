package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "\"hopdong\"") // Đảm bảo tên bảng khớp với cơ sở dữ liệu
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HopDong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"sohd\"") // Tên cột trong cơ sở dữ liệu
    private Long sohd;

    @Column(name = "\"ngaybatdau\"")
    private LocalDate ngayBatDau;

    @Column(name = "\"ngayketthuc\"")
    private LocalDate ngayKetThuc;

    @Column(name = "\"ngayky\"")
    private LocalDate ngayKy;

    @Column(name = "\"noidung\"")
    private String noiDung;

    @ManyToOne
    @JoinColumn(name = "\"manv\"") // Khóa ngoại tham chiếu đến bảng NhanVien
    private NhanVien nhanVien;

    // Getters và Setters (nếu không dùng Lombok)
    public Long getSohd() {
        return sohd;
    }

    public void setSohd(Long sohd) {
        this.sohd = sohd;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public LocalDate getNgayKy() {
        return ngayKy;
    }

    public void setNgayKy(LocalDate ngayKy) {
        this.ngayKy = ngayKy;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
}