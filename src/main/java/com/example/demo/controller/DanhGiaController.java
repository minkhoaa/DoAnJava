package com.example.demo.controller;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.DanhGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/danhgia")
public class DanhGiaController {
    @Autowired
    private DanhGiaService danhGiaService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllDanhGia() {
        return ResponseEntity.ok(danhGiaService.getAllDanhGia());
    }
}
