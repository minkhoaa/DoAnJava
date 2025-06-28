package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.request.ChucVuDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.ChucVuService;

@RestController
@RequestMapping("/api/chucvu")
public class ChucVuController {

    @Autowired
    private ChucVuService chucVuService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllChucVu() {
        return ResponseEntity.ok(chucVuService.getAllChucVu());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse> getChucVuById(@PathVariable Long id) {
        return ResponseEntity.ok(chucVuService.getChucVuById(id));
    }

    @PostMapping("/addChucVu")
    public ResponseEntity<ApiResponse> addChucVu(@RequestBody ChucVuDto chucVuDto) {
        return ResponseEntity.ok(chucVuService.addChucVu(chucVuDto));
    }

    @PutMapping("/updateChucVu/{id}")
    public ResponseEntity<ApiResponse> updateChucVu(@PathVariable Long id, @RequestBody ChucVuDto chucVuDto) {
        return ResponseEntity.ok(chucVuService.updateChucVu(id, chucVuDto));
    }

    @DeleteMapping("/deleteChucVu/{id}")
    public ResponseEntity<ApiResponse> deleteChucVu(@PathVariable Long id) {
        return ResponseEntity.ok(chucVuService.deleteChucVu(id));
    }
}
