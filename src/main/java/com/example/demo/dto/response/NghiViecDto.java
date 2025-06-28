package com.example.demo.dto.response;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NghiViecDto {

    private Long id;
    private Long manv;
    private String hoten;
    private LocalDate tungay;
     private LocalDate denngay;
    private String lyDo;
    private String quyetDinh;

}
