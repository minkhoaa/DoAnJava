package com.example.demo.controller;

import com.example.demo.dto.request.CheckInDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.ChamCongService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.Locale;


@RestController
@RequestMapping("/api/chamcong")
public class ChamCongController {
    @Autowired
    private ChamCongService chamCongService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllChamCong(LocalDate ngay, Long employeeId) {
        return  ResponseEntity.ok(chamCongService.getAllChamCong(ngay, employeeId));
    }
    @PostMapping("/checkIn")
    public ResponseEntity<ApiResponse> checkIn(@RequestBody CheckInDto checkInDto) {
        var result = chamCongService.checkIn(checkInDto);
        if (result==null)  return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse("Unauthorized", null));
        return ResponseEntity.ok(result);

    }
}
