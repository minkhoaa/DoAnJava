package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.request.TrinhDoDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.TrinhDoService;

@RestController
@RequestMapping("/api/trinhdo")
public class TrinhDoController {

    @Autowired
    private TrinhDoService trinhDoService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllTrinhDo() {
        return ResponseEntity.ok(trinhDoService.getAllTrinhDo());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse> getTrinhDoById(@PathVariable Long id) {
        return ResponseEntity.ok(trinhDoService.getTrinhDoById(id));
    }

    @PostMapping("/addTrinhDo")
    public ResponseEntity<ApiResponse> addTrinhDo(@RequestBody TrinhDoDto trinhDoDto) {
        return ResponseEntity.ok(trinhDoService.addTrinhDo(trinhDoDto));
    }

    @PutMapping("/updateTrinhDo/{id}")
    public ResponseEntity<ApiResponse> updateTrinhDo(@PathVariable Long id, @RequestBody TrinhDoDto trinhDoDto) {
        return ResponseEntity.ok(trinhDoService.updateTrinhDo(id, trinhDoDto));
    }

    @DeleteMapping("/deleteTrinhDo/{id}")
    public ResponseEntity<ApiResponse> deleteTrinhDo(@PathVariable Long id) {
        return ResponseEntity.ok(trinhDoService.deleteTrinhDo(id));
    }
}