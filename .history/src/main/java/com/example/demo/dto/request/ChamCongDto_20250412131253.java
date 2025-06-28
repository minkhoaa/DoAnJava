package com.example.demo.dto.request;

import java.time.LocalTime;
import java.util.Set;

public class ChamCongDto {

    private Long employeeId;
    private LocalTime gioVao;
    private LocalTime gioRa;
    private Double soGioLam;
    private Set<Long> loaiCongIds; // Danh sách ID của loại công

    // Getters và Setters
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalTime getGioVao() {
        return gioVao;
    }

    public void setGioVao(LocalTime gioVao) {
        this.gioVao = gioVao;
    }

    public LocalTime getGioRa() {
        return gioRa;
    }

    public void setGioRa(LocalTime gioRa) {
        this.gioRa = gioRa;
    }

    public Double getSoGioLam() {
        return soGioLam;
    }

    public void setSoGioLam(Double soGioLam) {
        this.soGioLam = soGioLam;
    }

    public Set<Long> getLoaiCongIds() {
        return loaiCongIds;
    }

    public void setLoaiCongIds(Set<Long> loaiCongIds) {
        this.loaiCongIds = loaiCongIds;
    }
}