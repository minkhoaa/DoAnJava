package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.LoaiCong;
import com.example.demo.repository.LoaiCongRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class BangCongService {

    @Autowired
    private BangCongRepository bangCongRepository;

    @Autowired
    private LoaiCongRepository loaiCongRepository;

    // Lấy tất cả bảng công
    public ApiResponse getAllBangCong() {
        return new ApiResponse("Success", bangCongRepository.findAll());
    }

    // Lấy bảng công theo ID
    public ApiResponse getBangCongById(Long mabc) {
        Optional<BangCong> bangCong = bangCongRepository.findById(mabc);
        if (!bangCong.isPresent())
            return new ApiResponse("failed", "Không tồn tại bảng công này");

        return new ApiResponse("Success", bangCong.get());
    }

    // Thêm mới bảng công
    public ApiResponse addBangCong(BangCong bangCong, Set<Long> loaiCongIds) {
        Set<LoaiCong> loaiCong = loaiCongIds.stream()
                .map(id -> loaiCongRepository.findById(id).orElse(null))
                .filter(lc -> lc != null)
                .collect(java.util.stream.Collectors.toSet());

        return new ApiResponse("Success", bangCongRepository.save(bangCong));
    }
}