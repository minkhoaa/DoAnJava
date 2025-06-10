package com.example.demo.controller;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.DanhGiaDto;
import com.example.demo.entity.DanhGia;
import com.example.demo.repository.DanhGiaRepository;
import com.example.demo.service.TrinhDoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/danhgianew")
public class NewDanhGiaController {

    @Autowired
    private DanhGiaRepository danhGiaRepository;

    // cấp quyền
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllDanhGia() {
        List<DanhGia> danhGias = danhGiaRepository.findAll();
        List<DanhGiaDto> response = danhGias.stream().map(danhGia -> {
            DanhGiaDto dto = new DanhGiaDto();
            dto.setId(danhGia.getId());
            // Lấy thông tin nhân viên (ID và họ tên)
            dto.setNhanVien(null);
            dto.setNam(danhGia.getNam());
            dto.setKy(danhGia.getKy());
            dto.setDiemSo(danhGia.getDiemSo());
            dto.setNhanXet(danhGia.getNhanXet());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse("success", response));
    }
}
