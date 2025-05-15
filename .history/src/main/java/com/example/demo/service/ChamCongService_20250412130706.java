package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.ChamCongDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.ChamCong;
import com.example.demo.entity.NhanVien;
import com.example.demo.repository.ChamCongRepository;
import com.example.demo.repository.NhanVienRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChamCongService {

    @Autowired
    private ChamCongRepository chamCongRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private BangCongRepository bangCongRepository;

    // Lấy tất cả chấm công
    public ApiResponse getAllChamCong() {
        return new ApiResponse("Success", chamCongRepository.findAll());
    }

    // Lấy chấm công theo ID
    public ApiResponse getChamCongById(Long id) {
        Optional<ChamCong> chamCong = chamCongRepository.findById(id);
        if (!chamCong.isPresent())
            return new ApiResponse("failed", "Không tồn tại chấm công này");

        return new ApiResponse("Success", chamCong.get());
    }

    // Thêm mới chấm công
    public ApiResponse addChamCong(ChamCongDto chamCongDto) {
        Optional<NhanVien> nhanVienOptional = nhanVienRepository.findById(chamCongDto.getEmployeeId());
        if (!nhanVienOptional.isPresent())
            return new ApiResponse("failed", "Không tồn tại nhân viên với mã này");

        Set<BangCong> loaiCong = chamCongDto.getLoaiCongIds().stream()
                .map(id -> bangCongRepository.findById(id).orElse(null))
                .filter(bangCong -> bangCong != null)
                .collect(Collectors.toSet());

        ChamCong chamCong = new ChamCong();
        chamCong.setNhanVien(nhanVienOptional.get());
        chamCong.setNgay(chamCongDto.getNgay());
        chamCong.setGioVao(chamCongDto.getGioVao());
        chamCong.setGioRa(chamCongDto.getGioRa());
        chamCong.setSoGioLam(chamCongDto.getSoGioLam());
        chamCong.setLoaiCong(loaiCong);

        return new ApiResponse("Success", chamCongRepository.save(chamCong));
    }

    // Cập nhật chấm công
    public ApiResponse updateChamCong(Long id, ChamCongDto chamCongDto) {
        Optional<ChamCong> chamCongOptional = chamCongRepository.findById(id);
        if (!chamCongOptional.isPresent())
            return new ApiResponse("failed", "Không tồn tại chấm công này");

        ChamCong chamCong = chamCongOptional.get();
        if (chamCongDto.getNgay() != null) {
            chamCong.setNgay(chamCongDto.getNgay());
        }
        if (chamCongDto.getGioVao() != null) {
            chamCong.setGioVao(chamCongDto.getGioVao());
        }
        if (chamCongDto.getGioRa() != null) {
            chamCong.setGioRa(chamCongDto.getGioRa());
        }
        if (chamCongDto.getSoGioLam() != null) {
            chamCong.setSoGioLam(chamCongDto.getSoGioLam());
        }
        if (chamCongDto.getLoaiCongIds() != null) {
            Set<BangCong> loaiCong = chamCongDto.getLoaiCongIds().stream()
                    .map(id -> bangCongRepository.findById(id).orElse(null))
                    .filter(bangCong -> bangCong != null)
                    .collect(Collectors.toSet());
            chamCong.setLoaiCong(loaiCong);
        }

        return new ApiResponse("Success", chamCongRepository.save(chamCong));
    }

    // Xóa chấm công
    public ApiResponse deleteChamCong(Long id) {
        Optional<ChamCong> chamCongOptional = chamCongRepository.findById(id);
        if (!chamCongOptional.isPresent())
            return new ApiResponse("failed", "Không tồn tại chấm công này");

        chamCongRepository.deleteById(id);
        return new ApiResponse("Success", "Xóa thành công: " + chamCongOptional.get());
    }
}