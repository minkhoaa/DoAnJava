package com.example.demo.dto.request;

import jakarta.persistence.Column;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhongBanDto {
    private Long idpb;
    private String tenpb;
}