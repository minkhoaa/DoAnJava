package com.example.demo.controller;

import com.example.demo.dto.request.CheckIn_OutDto;
import com.example.demo.dto.request.ModifiedCheckIn_OutDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.ChamCongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


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
    public ResponseEntity<ApiResponse> checkIn(@RequestBody CheckIn_OutDto checkInDto) {
        var result = chamCongService.checkIn(checkInDto);

        return ResponseEntity.ok(result);

    }
    @PostMapping("/checkOut")
    public ResponseEntity<ApiResponse> checkOut(@RequestBody CheckIn_OutDto checkOutDto) {
        var result = chamCongService.checkOut(checkOutDto);

        return ResponseEntity.ok(result);
    }

    @PutMapping("/modifierChamcong")
    public ResponseEntity<ApiResponse> modifierChamCong(@RequestBody ModifiedCheckIn_OutDto dto) {
        var result = chamCongService.ModifierChamCong(dto);
        return ResponseEntity.ok(result);

    }
}
