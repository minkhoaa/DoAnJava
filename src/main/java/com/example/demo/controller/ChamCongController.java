package com.example.demo.controller;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.ChamCongService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/chamcong")
public class ChamCongController {
    @Autowired
    private ChamCongService chamCongService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllChamCong() {
        return  ResponseEntity.ok(chamCongService.getAllChamCong());
    }


}
