package com.example.demo.dto.response;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeclineNghiviec {
    private Long id;
    private Long manv;
    private String hoten;
    private LocalDate tungay;
    private LocalDate denngay;
    private String lyDo;
    private String quyetDinh;
    private String liDo;
}
