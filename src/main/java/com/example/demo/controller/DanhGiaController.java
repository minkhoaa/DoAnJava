package com.example.demo.controller;

import com.example.demo.dto.request.AddDanhGiaDto;
import com.example.demo.dto.request.DanhGiaInputDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.DanhGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/danhgia")
public class DanhGiaController {
    @Autowired
    private DanhGiaService danhGiaService;

    @PostMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllDanhGia(@RequestBody DanhGiaInputDto danhGiaInputDto) {
        return ResponseEntity.ok(danhGiaService.getAllDanhGia(danhGiaInputDto));
    }
    @PostMapping("/addDanhGia")
    public ResponseEntity<ApiResponse> addDanhGia(@RequestBody AddDanhGiaDto danhGiaInputDto  ) {
        return ResponseEntity.ok(danhGiaService.addDanhGia(danhGiaInputDto));
    }
}
