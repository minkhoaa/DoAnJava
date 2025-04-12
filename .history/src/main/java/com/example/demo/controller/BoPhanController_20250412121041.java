package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.request.BoPhanDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.BoPhanService;

@RestController
@RequestMapping("/api/bophan")
public class BoPhanController {

    @Autowired
    private BoPhanService boPhanService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllBoPhan() {
        return ResponseEntity.ok(boPhanService.getAllBoPhan());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse> getBoPhanByID(@PathVariable Long id) {
        return ResponseEntity.ok(boPhanService.getBoPhanById(id));
    }

    @PostMapping("/addBoPhan")
    public ResponseEntity<ApiResponse> addBoPhan(@RequestBody BoPhanDto boPhanDto) {
        return ResponseEntity.ok(boPhanService.addBoPhan(boPhanDto));
    }

    @PutMapping("/updateBoPhan/{id}")
    public ResponseEntity<ApiResponse> updateBoPhan(@PathVariable Long id, @RequestBody BoPhanDto boPhanDto) {
        return ResponseEntity.ok(boPhanService.updateBoPhan(id, boPhanDto));
    }

    @DeleteMapping("/deleteBoPhan/{id}")
    public ResponseEntity<ApiResponse> deleteBoPhan(@PathVariable Long id) {
        return ResponseEntity.ok(boPhanService.deleteBoPhan(id));
    }
}
