package com.example.demo.controller;


import com.example.demo.dto.request.PhieuLuongInputDto;
import com.example.demo.dto.request.TokenRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.LuongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/luong")
public class LuongController {
    @Autowired
    private LuongService luongService;
    @PostMapping("/getFilteredPhieuLuong")
    public ResponseEntity<ApiResponse> getFilteredPhieuLuong(@RequestBody PhieuLuongInputDto phieuLuongInputDto) {
        return ResponseEntity.ok(luongService.getFilteredPhieuLuong(phieuLuongInputDto));
    }
    @PostMapping("/getAllPhieuLuong")
    public ResponseEntity<ApiResponse> getAllPhieuLuongs(@RequestBody TokenRequest tokenRequest) {
        return ResponseEntity.ok(luongService.getAllPhieuLuong(tokenRequest.getToken()));
    }
}
