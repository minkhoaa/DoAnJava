package com.example.demo.dto.request;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class AddNghiViecDto {
    private LocalDate tungay;
    private LocalDate denngay;
    private String lyDo;

}
