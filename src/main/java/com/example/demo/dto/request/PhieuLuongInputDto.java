package com.example.demo.dto.request;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhieuLuongInputDto {
    public Long employeeId;
    public Integer thang;
    public Integer nam;
}
