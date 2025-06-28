package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.repository.PhongBanRepository;

import jakarta.websocket.server.ServerEndpoint;

@Service
public class PhongBanService {
    @Autowired
    private PhongBanRepository phongBanRepository;
    

    public ApiResponse getAllPhongBan() {
        return new ApiResponse("Success", phongBanRepository.findAll());
    }
}
