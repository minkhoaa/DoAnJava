package com.example.demo.dto.request;

import java.time.LocalDate;

import jakarta.persistence.Column;

public class NhanVienDto {

    private String hoten;

    private String gioitinh;

    private LocalDate ngaysinh;

    private String dienthoai;

    private String cccd;

    private String diachi;

    private String hinhanh;

    private Long idpb;

    private Long idcv;

    private Long idtd;

    public NhanVienDto(String hoten, String gioitinh, LocalDate ngaysinh, String dienthoai, String cccd, String diachi,
            String hinhanh, Long idpb, Long idcv, Long idtd) {
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.dienthoai = dienthoai;
        this.cccd = cccd;
        this.diachi = diachi;
        this.hinhanh = hinhanh;
        this.idpb = idpb;
        this.idcv = idcv;
        this.idtd = idtd;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public LocalDate getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(LocalDate ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public Long getIdpb() {
        return idpb;
    }

    public void setIdpb(Long idpb) {
        this.idpb = idpb;
    }

    public Long getIdcv() {
        return idcv;
    }

    public void setIdcv(Long idcv) {
        this.idcv = idcv;
    }

    public Long getIdtd() {
        return idtd;
    }

    public void setIdtd(Long idtd) {
        this.idtd = idtd;
    }
}
