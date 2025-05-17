package com.example.demo.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DanhGiaInputDto {
    public String token;
    public Long employeeId;
    public Integer ky;
    public Integer nam;
}
