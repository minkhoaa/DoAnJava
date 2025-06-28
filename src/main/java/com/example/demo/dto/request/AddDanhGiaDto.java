package com.example.demo.dto.request;


import lombok.*;
import org.springframework.data.repository.NoRepositoryBean;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddDanhGiaDto {
    public String token;
    public Long employeeId;
    public Integer ky;
    public Integer nam;
    public Integer diemSo;
    public String nhanXet;
}
