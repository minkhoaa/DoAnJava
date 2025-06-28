package com.example.demo.dto.request;

import java.time.LocalDate;

public class BaoHiemDto {

    private String sobh;
    private LocalDate ngayCap;
    private String noiCap;
    private String noiKhamBenh;
    private Long manv; // ID của nhân viên

    // Getters và Setters
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

    public Long getManv() {
        return manv;
    }

    public void setManv(Long manv) {
        this.manv = manv;
    }
}