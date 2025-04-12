package com.example.demo.service;

import java.util.Optional;

import org.aspectj.internal.lang.annotation.ajcPrivileged;
import org.hibernate.jdbc.Expectations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ExceptionDepthComparator;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.PhongBanDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.PhongBan;
import com.example.demo.repository.PhongBanRepository;

import jakarta.websocket.server.ServerEndpoint;

@Service
public class PhongBanService {
    @Autowired
    private PhongBanRepository phongBanRepository;
    

    public ApiResponse getAllPhongBan() {
        return new ApiResponse("Success", phongBanRepository.findAll());
    }

    public ApiResponse getPhongBanById(Long id) {
        Optional<PhongBan> phongban = phongBanRepository.findById(id);
        if (!phongban.isPresent())
            return new ApiResponse("failed", null);

        PhongBan pb = phongban.get();
        return new ApiResponse("Success", pb);
    }

    public ApiResponse addPhongBan(PhongBanDto phongBanDto) {
        if (phongBanDto.getName() == null)
            return new ApiResponse("failed", null);
        PhongBan phongBan = new PhongBan();

        try {
            phongBan.setName(phongBanDto.getName());
            return new ApiResponse("Success", phongBanRepository.save(phongBan));
        } catch (Exception ex) {
            return new ApiResponse("failed", ex.getMessage());
        }
    }

    public ApiResponse updatePhongban(Long id, PhongBanDto phongBanDto) {
        Optional<PhongBan> phongban = phongBanRepository.findById(id);
        if (!phongban.isPresent())
            return new ApiResponse("failed", "Không tồn tại phòng ban này");

        return new ApiResponse("Success", phongban.get());
    }

    public ApiResponse deletePhongBan(Long id) {
        Optional<PhongBan> phongban = phongBanRepository.findById(id);
        if (!phongban.isPresent())
            return new ApiResponse("failed", "Không tồn tại phòng ban này");
        
        phongBanRepository.deleteById(id);
        return new ApiResponse("Sucess", "Xóa thành công: "+ phongban.get());
    }
 }
