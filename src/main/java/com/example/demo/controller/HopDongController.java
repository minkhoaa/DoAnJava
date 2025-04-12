package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.request.HopDongDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.HopDongService;

@RestController
@RequestMapping("/api/hopdong")
public class HopDongController {

    @Autowired
    private HopDongService hopDongService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllHopDong() {
        return ResponseEntity.ok(hopDongService.getAllHopDong());
    }

    @GetMapping("/getById/{sohd}")
    public ResponseEntity<ApiResponse> getHopDongById(@PathVariable Long sohd) {
        return ResponseEntity.ok(hopDongService.getHopDongById(sohd));
    }

    @PostMapping("/addHopDong")
    public ResponseEntity<ApiResponse> addHopDong(@RequestBody HopDongDto hopDongDto) {
        return ResponseEntity.ok(hopDongService.addHopDong(hopDongDto));
    }

    @PutMapping("/updateHopDong/{sohd}")
    public ResponseEntity<ApiResponse> updateHopDong(@PathVariable Long sohd, @RequestBody HopDongDto hopDongDto) {
        return ResponseEntity.ok(hopDongService.updateHopDong(sohd, hopDongDto));
    }

    @DeleteMapping("/deleteHopDong/{sohd}")
    public ResponseEntity<ApiResponse> deleteHopDong(@PathVariable Long sohd) {
        return ResponseEntity.ok(hopDongService.deleteHopDong(sohd));
    }
}