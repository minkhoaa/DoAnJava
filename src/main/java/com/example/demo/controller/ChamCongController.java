package com.example.demo.controller;

import com.example.demo.dto.request.ModifiedCheckIn_OutDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.ChamCongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/chamcong")
public class ChamCongController {
    @Autowired
    private ChamCongService chamCongService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllChamCong( @RequestParam(value = "ngay", required = false)
                                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ngay,

                                                       @RequestParam(value = "employId", required = false) Long employeeId) {
        return  ResponseEntity.ok(chamCongService.getAllChamCong(ngay, employeeId));
    }
    @PostMapping("/checkIn")
    public ResponseEntity<ApiResponse> checkIn() {

        var result = chamCongService.checkIn();

        return ResponseEntity.ok(result);

    }
    @PostMapping("/checkOut")
    public ResponseEntity<ApiResponse> checkOut() {
        var result = chamCongService.checkOut();

        return ResponseEntity.ok(result);
    }

    @PutMapping("/modifierChamcong")
    public ResponseEntity<ApiResponse> modifierChamCong(@RequestBody ModifiedCheckIn_OutDto dto) {
        var result = chamCongService.ModifierChamCong(dto);
        return ResponseEntity.ok(result);

    }
}
