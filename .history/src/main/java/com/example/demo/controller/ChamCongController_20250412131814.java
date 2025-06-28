package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.request.ChamCongDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.ChamCongService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/attendance")
public class ChamCongController {

    @Autowired
    private ChamCongService chamCongService;

    // GET: Lấy danh sách chấm công theo employeeId, date hoặc month
    @GetMapping
    public ResponseEntity<ApiResponse> getAttendance(
            @RequestParam(required = false) Long employeeId,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) Integer month) {
        return ResponseEntity.ok(chamCongService.getAttendance(employeeId, date, month));
    }

    // POST: Check-in
    @PostMapping("/check-in")
    public ResponseEntity<ApiResponse> checkIn(@RequestBody ChamCongDto chamCongDto) {
        return ResponseEntity.ok(chamCongService.checkIn(chamCongDto));
    }

    // POST: Check-out
    @PostMapping("/check-out")
    public ResponseEntity<ApiResponse> checkOut(@RequestBody ChamCongDto chamCongDto) {
        return ResponseEntity.ok(chamCongService.checkOut(chamCongDto));
    }

    // PUT: Cập nhật chấm công (Admin)
    @PutMapping("/{attendanceId}")
    public ResponseEntity<ApiResponse> updateAttendance(
            @PathVariable Long attendanceId,
            @RequestBody ChamCongDto chamCongDto) {
        return ResponseEntity.ok(chamCongService.updateAttendance(attendanceId, chamCongDto));
    }
}