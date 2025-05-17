package com.example.demo.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TuyenDungInputDto
{
    public String token;
    public String hoten;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate ngaysinh;
    public String dienthoai;
    public String email;
    public String vitri;

}
