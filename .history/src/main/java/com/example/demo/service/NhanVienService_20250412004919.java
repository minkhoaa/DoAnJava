package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.request.NhanVienDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.NhanVien;
import com.example.demo.exception.NhanVienNotFoundException;
import com.example.demo.repository.NhanVienRepository;

import jakarta.websocket.server.ServerEndpoint;

@Service
public class NhanVienService {

    private final AuthService authService;
    private NhanVienRepository nhanVienRepository;

    public NhanVienService(NhanVienRepository nhanVienRepository, AuthService authService) {
        this.nhanVienRepository = nhanVienRepository;
        this.authService = authService;
    }
    
    public ApiResponse getAllNhanVien()  {
        try{
        return new ApiResponse(
      "Success" , nhanVienRepository.findAll());
        
        } catch (Exception e) {
            return new ApiResponse("404 NOT FOUND", null);
        }
    }

    public ApiResponse getNhanVienByID(Long id) {
        Optional<NhanVien> nhanVien = nhanVienRepository.findById(id);
        return nhanVien.isPresent() ? new ApiResponse("Success", nhanVien.get()) : new ApiResponse("failed", null);
    }
        
    public ApiResponse addNhanVien(NhanVienDto nhanVienDto) {
        if (nhanVienDto.getHoten() == null || nhanVienDto.getHoten().isEmpty()) {
            return new ApiResponse("failed", "Họ tên không được để trống");
        }
        if (nhanVienDto.getCccd() == null || nhanVienDto.getCccd().isEmpty()) {
            return new ApiResponse("failed", "CCCD không được để trống");
        }
        NhanVien nhanVien = new NhanVien() ;
        nhanVien.setHoten(nhanVienDto.getHoten());
        nhanVien.setGioitinh(nhanVienDto.getGioitinh());
        nhanVien.setNgaysinh(nhanVienDto.getNgaysinh());
        nhanVien.setDienthoai(nhanVienDto.getDienthoai());
        nhanVien.setCccd(nhanVienDto.getCccd());
        nhanVien.setDiachi(nhanVienDto.getDiachi());
        nhanVien.setHinhanh(nhanVienDto.getHinhanh());
        nhanVien.setIdpb(nhanVienDto.getIdpb());
        nhanVien.setIdcv(nhanVienDto.getIdcv());
        nhanVien.setIdtd(nhanVienDto.getIdtd());
        nhanVienRepository.save(nhanVien);
        return new ApiResponse("Success", nhanVien);
    }
    }


