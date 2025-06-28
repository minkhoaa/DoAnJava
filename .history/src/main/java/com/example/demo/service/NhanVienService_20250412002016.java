package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.NhanVien;
import com.example.demo.repository.NhanVienRepository;

import jakarta.websocket.server.ServerEndpoint;

@Service
public class NhanVienService {
    private NhanVienRepository nhanVienRepository;

    public NhanVienService(NhanVienRepository nhanVienRepository) {
        this.nhanVienRepository = nhanVienRepository;
    }
    
    public ApiResponse getAllNhanVien() {
        
        return new ApiResponse(
      "Success" , nhanVienRepository.findAll());
    }

    public ApiResponse getNhanVienByID(Long id) {
        return new ApiResponse(
            "Success", nhanVienRepository.findById(id));
        }
    }


