package com.example.demo.service;

import com.example.demo.dto.request.AddNghiViecDto;
import com.example.demo.dto.response.NghiViecDto;
import com.example.demo.dto.response.NhanVienDto;
import com.example.demo.entity.ChamCong;
import com.example.demo.entity.LoaiCong;
import com.example.demo.entity.NghiViec;
import com.example.demo.entity.NhanVien;
import com.example.demo.repository.ChamCongRepository;
import com.example.demo.repository.LoaiCongRepository;
import com.example.demo.repository.NghiViecRepository;
import com.example.demo.repository.NhanVienRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NghiViecService {


    @Autowired
    private NghiViecRepository nghiViecRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private ChamCongRepository chamCongRepository;

    @Autowired
    private LoaiCongRepository loaiCongRepository;


    public List<com.example.demo.dto.response.NghiViecDto> GetAllByEmplopyeeId(Long id) {
        return nghiViecRepository.findByNhanVien_Id(id)
                .stream()
                .map(
                x -> {
                    NhanVien nhanVien = x.getNhanVien();
                    return new com.example.demo.dto.response.NghiViecDto(x.getId(), nhanVien.getId(), nhanVien.getHoten(), x.getTungay(), x.getDenngay(), x.getLyDo(), x.getQuyetDinh());
                }).collect(Collectors.toList());
    }
    public List<com.example.demo.dto.response.NghiViecDto> GetAll() {
        return nghiViecRepository.findAll()
                .stream()
                .map(
                        x -> {
                            NhanVien nhanVien = x.getNhanVien();
                            return new com.example.demo.dto.response.NghiViecDto(x.getId(), nhanVien.getId(), nhanVien.getHoten(), x.getTungay(), x.getDenngay(), x.getLyDo(), x.getQuyetDinh());
                        }).collect(Collectors.toList());
    }

    public NghiViecDto AddNewNghiViec(AddNghiViecDto nghiViecDto, Long employeeId) {
        NhanVien nhanVien = nhanVienRepository.findById(employeeId).get();

        NghiViec nghiViec = new NghiViec();
        nghiViec.setNhanVien(nhanVien);
        nghiViec.setTungay(nghiViecDto.getTungay());
        nghiViec.setDenngay(nghiViecDto.getDenngay());
        nghiViec.setLyDo(nghiViecDto.getLyDo());
        nghiViec.setQuyetDinh("Đang xét duyệt");
        nghiViecRepository.save(nghiViec);
        return new NghiViecDto(nghiViec.getId(), nghiViec.getNhanVien().getId(), nghiViec.getNhanVien().getHoten(), nghiViec.getTungay(), nghiViec.getDenngay(), nghiViec.getLyDo(), nghiViec.getQuyetDinh());

    }

    public NghiViecDto approveNghiViec(Long nghiViecId) {
        NghiViec nghiViec = nghiViecRepository.findById(nghiViecId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn nghỉ việc"));

        // 1. Cập nhật trạng thái đơn nghỉ việc
        nghiViec.setQuyetDinh("Đã duyệt");
        nghiViecRepository.save(nghiViec);

        // 2. Cập nhật bảng chấm công cho các ngày nghỉ
        NhanVien nv = nghiViec.getNhanVien();
        LocalDate tuNgay = nghiViec.getTungay();
        LocalDate denNgay = nghiViec.getDenngay();

        for (LocalDate date = tuNgay; !date.isAfter(denNgay); date = date.plusDays(1)) {
            // Kiểm tra đã có chấm công ngày này chưa
            ChamCong chamCong = chamCongRepository.findByEmployeeIdAndNgay(nv.getId(), date);
            if (chamCong == null) {
                chamCong = new ChamCong();
                chamCong.setEmployeeId(nv.getId());
                chamCong.setNgay(date);
                chamCongRepository.save(chamCong);

            }
            LoaiCong loaiCong = new LoaiCong();
            loaiCong.setHeSo(0.5);
            loaiCong.setMachamcong(chamCong.getId());
            loaiCong.setTenLc("Nghỉ có phép");
            loaiCongRepository.save(loaiCong);
        }

        return new NghiViecDto(
                nghiViec.getId(),
                nv.getId(),
                nv.getHoten(),
                nghiViec.getTungay(),
                nghiViec.getDenngay(),
                nghiViec.getLyDo(),
                nghiViec.getQuyetDinh()
        );
    }

}
