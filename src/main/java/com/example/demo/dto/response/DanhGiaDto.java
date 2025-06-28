package com.example.demo.dto.response;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DanhGiaDto {
    private Long id;
    private Integer diemSo;
    private Integer ky;
    private Integer nam;
    private String nhanXet;
    private NhanVienDto nhanVien;
}