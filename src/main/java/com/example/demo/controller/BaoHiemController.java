package com.example.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.request.BaoHiemDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.BaoHiemService;

@RestController
@RequestMapping("/api/baohiem")
public class BaoHiemController {

    @Autowired
    private BaoHiemService baoHiemService;
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllBaoHiem() {
        return ResponseEntity.ok(baoHiemService.getAllBaoHiem());
    }

    @GetMapping("/getById/{idbh}")
    public ResponseEntity<ApiResponse> getBaoHiemById(@PathVariable Long idbh) {
        return ResponseEntity.ok(baoHiemService.getBaoHiemById(idbh));
    }

    @PostMapping("/addBaoHiem")

    public ResponseEntity<ApiResponse> addBaoHiem(@RequestBody BaoHiemDto baoHiemDto) {
        return ResponseEntity.ok(baoHiemService.addBaoHiem(baoHiemDto));
    }

    @PutMapping("/updateBaoHiem/{idbh}")
    public ResponseEntity<ApiResponse> updateBaoHiem(@PathVariable Long idbh, @RequestBody BaoHiemDto baoHiemDto) {
        return ResponseEntity.ok(baoHiemService.updateBaoHiem(idbh, baoHiemDto));
    }

    @DeleteMapping("/deleteBaoHiem/{idbh}")
    public ResponseEntity<ApiResponse> deleteBaoHiem(@PathVariable Long idbh) {
        return ResponseEntity.ok(baoHiemService.deleteBaoHiem(idbh));
    }
}