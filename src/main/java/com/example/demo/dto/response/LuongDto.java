package com.example.demo.dto.response;

import lombok.*;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LuongDto {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private Integer thang;
    private Integer nam;
    private BigDecimal luongCoBan;
    private BigDecimal phuCap;
    private BigDecimal baoHiem;
    private BigDecimal thuNhapThuc;

}

