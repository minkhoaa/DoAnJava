package com.example.demo.dto.request;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
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


}
