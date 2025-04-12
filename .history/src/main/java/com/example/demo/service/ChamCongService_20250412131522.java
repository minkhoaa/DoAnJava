package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.ChamCongDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.ChamCong;
import com.example.demo.entity.LoaiCong;
import com.example.demo.entity.NhanVien;
import com.example.demo.repository.ChamCongRepository;
import com.example.demo.repository.LoaiCongRepository;
import com.example.demo.repository.NhanVienRepository;

import java.time.LocalDate;
import java.time.LocalTime;
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
    private LoaiCongRepository loaiCongRepository;

    // GET: Lấy danh sách chấm công theo employeeId, date hoặc month
    public ApiResponse getAttendance(Long employeeId, LocalDate date, Integer month) {
        if (employeeId != null && date != null) {
            return new ApiResponse("Success", chamCongRepository.findByNhanVienIdAndNgay(employeeId, date));
        } else if (employeeId != null && month != null) {
            return new ApiResponse("Success", chamCongRepository.findByNhanVienIdAndMonth(employeeId, month));
        } else {
            return new ApiResponse("Success", chamCongRepository.findAll());
        }
    }

    // POST: Check-in
    public ApiResponse checkIn(ChamCongDto chamCongDto) {
        Optional<NhanVien> nhanVienOptional = nhanVienRepository.findById(chamCongDto.getEmployeeId());
        if (!nhanVienOptional.isPresent()) {
            return new ApiResponse("failed", "Không tồn tại nhân viên với mã này");
        }

        ChamCong chamCong = new ChamCong();
        chamCong.setNhanVien(nhanVienOptional.get());
        chamCong.setNgay(LocalDate.now());
        chamCong.setGioVao(chamCongDto.getGioVao() != null ? chamCongDto.getGioVao() : LocalTime.now());

        // Gán loại công nếu có
        if (chamCongDto.getLoaiCongIds() != null) {
            Set<LoaiCong> loaiCong = chamCongDto.getLoaiCongIds().stream()
                    .map(id -> loaiCongRepository.findById(id).orElse(null))
                    .filter(lc -> lc != null)
                    .collect(Collectors.toSet());
            chamCong.setLoaiCong(loaiCong);
        }

        return new ApiResponse("Success", chamCongRepository.save(chamCong));
    }

    // POST: Check-out
    public ApiResponse checkOut(ChamCongDto chamCongDto) {
        Optional<ChamCong> chamCongOptional = chamCongRepository.findByNhanVienIdAndNgay(
                chamCongDto.getEmployeeId(), LocalDate.now());
        if (!chamCongOptional.isPresent()) {
            return new ApiResponse("failed", "Không tìm thấy bản ghi check-in cho nhân viên này hôm nay");
        }

        ChamCong chamCong = chamCongOptional.get();
        chamCong.setGioRa(chamCongDto.getGioRa() != null ? chamCongDto.getGioRa() : LocalTime.now());

        // Tính số giờ làm
        if (chamCong.getGioVao() != null && chamCong.getGioRa() != null) {
            chamCong.setSoGioLam((double) (chamCong.getGioRa().toSecondOfDay() - chamCong.getGioVao().toSecondOfDay()) / 3600);
        }

        return new ApiResponse("Success", chamCongRepository.save(chamCong));
    }

    // PUT: Cập nhật chấm công (Admin)
    public ApiResponse updateAttendance(Long attendanceId, ChamCongDto chamCongDto) {
        Optional<ChamCong> chamCongOptional = chamCongRepository.findById(attendanceId);
        if (!chamCongOptional.isPresent()) {
            return new ApiResponse("failed", "Không tồn tại bản ghi chấm công này");
        }

        ChamCong chamCong = chamCongOptional.get();
        if (chamCongDto.getGioVao() != null) {
            chamCong.setGioVao(chamCongDto.getGioVao());
        }
        if (chamCongDto.getGioRa() != null) {
            chamCong.setGioRa(chamCongDto.getGioRa());
        }
        if (chamCongDto.getSoGioLam() != null) {
            chamCong.setSoGioLam(chamCongDto.getSoGioLam());
        }

        // Gán loại công nếu có
        if (chamCongDto.getLoaiCongIds() != null) {
            Set<LoaiCong> loaiCong = chamCongDto.getLoaiCongIds().stream()
                    .map(id -> loaiCongRepository.findById(id).orElse(null))
                    .filter(lc -> lc != null)
                    .collect(Collectors.toSet());
            chamCong.setLoaiCong(loaiCong);
        }

        return new ApiResponse("Success", chamCongRepository.save(chamCong));
    }
}