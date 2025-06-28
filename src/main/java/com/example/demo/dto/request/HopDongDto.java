package com.example.demo.dto.request;

import java.time.LocalDate;

public class HopDongDto {

    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private LocalDate ngayKy;
    private String noiDung;
    private Long manv; // ID của nhân viên

    // Getters và Setters
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

    public Long getManv() {
        return manv;
    }

    public void setManv(Long manv) {
        this.manv = manv;
    }
}