package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.request.ChamCongDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.ChamCongService;

@RestController
@RequestMapping("/api/chamcong")
public class ChamCongController {

    @Autowired
    private ChamCongService chamCongService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllChamCong() {
        return ResponseEntity.ok(chamCongService.getAllChamCong());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse> getChamCongById(@PathVariable Long id) {
        return ResponseEntity.ok(chamCongService.getChamCongById(id));
    }

    @PostMapping("/addChamCong")
    public ResponseEntity<ApiResponse> addChamCong(@RequestBody ChamCongDto chamCongDto) {
        return ResponseEntity.ok(chamCongService.addChamCong(chamCongDto));
    }

    @PutMapping("/updateChamCong/{id}")
    public ResponseEntity<ApiResponse> updateChamCong(@PathVariable Long id, @RequestBody ChamCongDto chamCongDto) {
        return ResponseEntity.ok(chamCongService.updateChamCong(id, chamCongDto));
    }

    @DeleteMapping("/deleteChamCong/{id}")
    public ResponseEntity<ApiResponse> deleteChamCong(@PathVariable Long id) {
        return ResponseEntity.ok(chamCongService.deleteChamCong(id));
    }
}