package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.PhongBanService;

@RestController
@RequestMapping("/api/phongban")
public class PhongBanController {
    @Autowired
    private PhongBanService phongBanService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllPhongBan() {
        return ResponseEntity.ok(phongBanService.getAllPhongBan());
    }
}
