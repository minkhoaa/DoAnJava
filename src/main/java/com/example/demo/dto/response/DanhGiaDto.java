package com.example.demo.dto.response;

import com.example.demo.dto.request.NhanVienDto;
import lombok.Data;

@Data
public class DanhGiaDto {
    private Long id;
    private Integer diemSo;
    private Integer ky;
    private Integer nam;
    private String nhanXet;
    private NhanVienDto nhanVien;
}