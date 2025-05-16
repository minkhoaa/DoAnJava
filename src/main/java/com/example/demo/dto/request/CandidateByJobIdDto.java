package com.example.demo.dto.request;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateByJobIdDto {
    public String token;
    public Long jobId;
}
