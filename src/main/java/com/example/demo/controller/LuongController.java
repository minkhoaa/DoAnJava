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
    @GetMapping("/getAllPhieuLuong")
    public ResponseEntity<ApiResponse> getAllPhieuLuongs() {
        return ResponseEntity.ok(luongService.getAllPhieuLuong());
    }
    @GetMapping("/Calculate")
    public ResponseEntity<ApiResponse> calculateLuong(
            @RequestParam("employeeId") Long employeeId,
            @RequestParam("thang") Integer thang,
            @RequestParam("nam") Integer nam
    ) {
        PhieuLuongInputDto dto = new PhieuLuongInputDto();
        dto.setEmployeeId(employeeId);
        dto.setThang(thang);
        dto.setNam(nam);
        return ResponseEntity.ok(luongService.calculateLuong(dto));
    }
}
