package com.example.demo.service;

import com.example.demo.dto.request.AddDanhGiaDto;
import com.example.demo.dto.request.NhanVienDto;
import com.example.demo.dto.response.DanhGiaDto;
import com.example.demo.entity.DanhGia;
import com.example.demo.entity.NhanVien;
import com.example.demo.repository.DanhGiaRepository;
import com.example.demo.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.TrinhDoDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.TrinhDo;
import com.example.demo.repository.TrinhDoRepository;

import java.util.Optional;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
public class DanhGiaService {
    @Autowired
    private DanhGiaRepository danhGiaRepository;
    @Autowired
    private NhanVienRepository nhanVienRepository;

    public ApiResponse getAllDanhGia() {
        DanhGiaDto response = new DanhGiaDto();
        DanhGia danhGia = new DanhGia();
        response.setId(danhGia.getId());
        response.setKy(danhGia.getKy());
        response.setNam(danhGia.getNam());
        response.setNhanXet(danhGia.getNhanXet());
        NhanVien nhanVienDto = danhGia.getNhanVien();
        response.setNhanVien(null);
        return new ApiResponse("Success", response );
    }

    public ApiResponse addDanhGia(AddDanhGiaDto dto) {

        Optional<NhanVien> nhanVienOptional = nhanVienRepository.findById(dto.getEmployeeId());
        if (!nhanVienOptional.isPresent()) return new ApiResponse("Not Found Employee", null);
        DanhGia danhGia = new DanhGia();
        danhGia.setNhanVien(nhanVienOptional.get());
        danhGia.setNam(dto.getNam());
        danhGia.setKy(dto.getKy());
        danhGia.setDiemSo(dto.getDiemSo());
        danhGia.setNhanXet(dto.getNhanXet());
        danhGiaRepository.save(danhGia);
        return new ApiResponse("Success", danhGia);
    }

}

