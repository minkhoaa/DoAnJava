package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.BangCongService;

import java.util.Set;

@RestController
@RequestMapping("/api/bangcong")
public class BangCongController {

    @Autowired
    private BangCongService bangCongService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllBangCong() {
        return ResponseEntity.ok(bangCongService.getAllBangCong());
    }

    @GetMapping("/getById/{mabc}")
    public ResponseEntity<ApiResponse> getBangCongById(@PathVariable Long mabc) {
        return ResponseEntity.ok(bangCongService.getBangCongById(mabc));
    }

    @PostMapping("/addBangCong")
    public ResponseEntity<ApiResponse> addBangCong(@RequestBody BangCong bangCong, @RequestParam Set<Long> loaiCongIds) {
        return ResponseEntity.ok(bangCongService.addBangCong(bangCong, loaiCongIds));
    }
}