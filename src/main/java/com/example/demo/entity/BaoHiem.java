package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "\"baohiem\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaoHiem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"idbh\"") 
    private Long idbh;

    @Column(name = "\"sobh\"")
    private String sobh;

    @Column(name = "\"ngaycap\"")
    private LocalDate ngayCap;

    @Column(name = "\"noicap\"")
    private String noiCap;

    @Column(name = "\"noikhambenh\"")
    private String noiKhamBenh;

    @ManyToOne
    @JoinColumn(name = "\"manv\"") 
    private NhanVien nhanVien;

    public Long getIdbh() {
        return idbh;
    }

    public void setIdbh(Long idbh) {
        this.idbh = idbh;
    }

    public String getSobh() {
        return sobh;
    }

    public void setSobh(String sobh) {
        this.sobh = sobh;
    }

    public LocalDate getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(LocalDate ngayCap) {
        this.ngayCap = ngayCap;
    }

    public String getNoiCap() {
        return noiCap;
    }

    public void setNoiCap(String noiCap) {
        this.noiCap = noiCap;
    }

    public String getNoiKhamBenh() {
        return noiKhamBenh;
    }

    public void setNoiKhamBenh(String noiKhamBenh) {
        this.noiKhamBenh = noiKhamBenh;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
}