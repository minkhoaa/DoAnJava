package com.example.demo.service;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.DanhGia;
import com.example.demo.repository.DanhGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhGiaService {
    @Autowired
    private AuthService authService;
    @Autowired
    private DanhGiaRepository danhGiaRepository;

    public ApiResponse getAllDanhGia() {
        return new ApiResponse("Success", danhGiaRepository.findAll());
    }


}
