package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.ChucVu;
import com.example.demo.entity.PhongBan;
import com.example.demo.entity.TrinhDo;
import com.example.demo.repository.ChucVuRepository;
import com.example.demo.repository.PhongBanRepository;
import com.example.demo.repository.TrinhDoRepository;
import org.aspectj.internal.lang.annotation.ajcPrivileged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.controller.NhanVienController;
import com.example.demo.dto.request.NhanVienDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.NhanVien;
import com.example.demo.exception.NhanVienNotFoundException;
import com.example.demo.repository.NhanVienRepository;

import jakarta.websocket.server.ServerEndpoint;

@Service
public class NhanVienService {

    private final AuthService authService;
    @Autowired
    private ChucVuRepository chucVuRepository;

    @Autowired
    private PhongBanRepository phongBanRepository;

    @Autowired
    private TrinhDoRepository trinhDoRepository;

    @Autowired
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
        NhanVien nhanVien = new NhanVien();

        nhanVien.setHoten(nhanVienDto.getHoten());
        nhanVien.setGioitinh(nhanVienDto.getGioitinh());
        nhanVien.setNgaysinh(nhanVienDto.getNgaysinh());
        nhanVien.setDienthoai(nhanVienDto.getDienthoai());
        nhanVien.setCccd(nhanVienDto.getCccd());
        nhanVien.setDiachi(nhanVienDto.getDiachi());
        nhanVien.setHinhanh(nhanVienDto.getHinhanh());

        // Gán chức vụ nếu có idcv
        if (nhanVienDto.getIdcv() != null) {
            ChucVu chucVu = chucVuRepository.findById(nhanVienDto.getIdcv())
                    .orElseThrow(() -> new RuntimeException("Chức vụ không tồn tại"));
            nhanVien.setChucVu(chucVu);
        } else {
            nhanVien.setChucVu(null); // Không có chức vụ
        }

        // Gán phòng ban nếu có idpb
        if (nhanVienDto.getIdpb() != null) {
            PhongBan phongBan = phongBanRepository.findById(nhanVienDto.getIdpb())
                    .orElseThrow(() -> new RuntimeException("Phòng ban không tồn tại"));
            nhanVien.setPhongBan(phongBan);
        } else {
            nhanVien.setPhongBan(null);
        }

        // Gán trình độ nếu có idtd
        if (nhanVienDto.getIdtd() != null) {
            TrinhDo trinhDo = trinhDoRepository.findById(nhanVienDto.getIdtd())
                    .orElseThrow(() -> new RuntimeException("Trình độ không tồn tại"));
            nhanVien.setTrinhDo(trinhDo);
        } else {
            nhanVien.setTrinhDo(null);
        }

        nhanVienRepository.save(nhanVien);

        return new ApiResponse("Success", nhanVien);
    }

    public ApiResponse updatenhanVien(Long id, NhanVienDto nhanVienDto) {
        Optional<NhanVien> nhanVienOptional = nhanVienRepository.findById(id);
        if (!nhanVienOptional.isPresent())
            return new ApiResponse("failed", "Nhân viên không tồn tại");

        NhanVien nhanVien = nhanVienOptional.get();
        if (nhanVienDto.getHoten() != null) {
            nhanVien.setHoten(nhanVienDto.getHoten());
        }
        if (nhanVienDto.getGioitinh() != null) {
            nhanVien.setGioitinh(nhanVienDto.getGioitinh());
        }
        if (nhanVienDto.getNgaysinh() != null) {
            nhanVien.setNgaysinh(nhanVienDto.getNgaysinh());
        }
        if (nhanVienDto.getDienthoai() != null) {
            nhanVien.setDienthoai(nhanVienDto.getDienthoai());
        }
        if (nhanVienDto.getCccd() != null) {
            nhanVien.setCccd(nhanVienDto.getCccd());
        }
        if (nhanVienDto.getDiachi() != null) {
            nhanVien.setDiachi(nhanVienDto.getDiachi());
        }
        if (nhanVienDto.getHinhanh() != null) {
            nhanVien.setHinhanh(nhanVienDto.getHinhanh());
        }
        if (nhanVienDto.getIdcv() != null) {
            ChucVu chucVu = chucVuRepository.findById(nhanVienDto.getIdcv())
                    .orElse(null); // hoặc throw nếu muốn báo lỗi
            nhanVien.setChucVu(chucVu);
        }

        // Mapping PhongBan
        if (nhanVienDto.getIdpb() != null) {
            PhongBan phongBan = phongBanRepository.findById(nhanVienDto.getIdpb())
                    .orElse(null);
            nhanVien.setPhongBan(phongBan);
        }

        // Mapping TrinhDo
        if (nhanVienDto.getIdtd() != null) {
            TrinhDo trinhDo = trinhDoRepository.findById(nhanVienDto.getIdtd())
                    .orElse(null);
            nhanVien.setTrinhDo(trinhDo);
        }

        nhanVienRepository.save(nhanVien);
        return new ApiResponse("Success", nhanVien);
    }
    
    public ApiResponse deleteNhanVien(Long id) {
        Optional<NhanVien> nhanVienOptional = nhanVienRepository.findById(id);

        NhanVien nhanVien = nhanVienOptional.get();

        if (nhanVien == null)
            return new ApiResponse("failed", "Nhân viên không tồn tại");

        nhanVienRepository.delete(nhanVien);
            return new ApiResponse("Success", nhanVien);
    }

    }


