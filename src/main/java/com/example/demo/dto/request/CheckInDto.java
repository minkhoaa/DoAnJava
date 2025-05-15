package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CheckInDto {
    public String token;
    public Long employeeId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate timeStamp;

    @Schema(type = "string", example = "08:00:00", format = "time")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime gioVao;

    @Schema(type = "string", example = "17:00:00", format = "time")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime gioRa;





}
