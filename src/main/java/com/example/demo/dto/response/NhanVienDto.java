package com.example.demo.dto.response;

import java.time.LocalDate;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter

public class NhanVienDto {
    private Long id; // Thêm id để xác định nhân viên
    private String hoten;
    private String gioitinh;
    private LocalDate ngaysinh;
    private String dienthoai;
    private String cccd;
    private String diachi;
    private String hinhanh;
    private Long idcv; // ID của chức vụ
    private String tenChucVu; // Tên chức vụ
    private Long idpb; // ID của phòng ban
    private String tenPhongBan; // Tên phòng ban
    private Long idtd; // ID của trình độ
    private String tenTrinhDo; // Tên trình độ
}
