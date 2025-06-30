package com.example.demo.service;

import com.example.demo.dto.request.AddNghiViecDto;
import com.example.demo.dto.response.DeclineNghiviec;
import com.example.demo.dto.response.NghiViecDto;
import com.example.demo.dto.response.NhanVienDto;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Data;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private UserRepository userRepository;

    public List<com.example.demo.dto.response.NghiViecDto> GetAllByEmplopyeeId(Long userId) {
        User user = userRepository.findByUserid(userId);
        Long nhanVienId = user.getEmployee().getId();

        return nghiViecRepository.findByNhanVien_Id(nhanVienId)
                .stream()
                .map(
                x -> {
                    NhanVien nhanVien = x.getNhanVien();
                    return new com.example.demo.dto.response.NghiViecDto(x.getId(), nhanVien.getId(), nhanVien.getHoten(), x.getTungay(), x.getDenngay(), x.getLyDo(), x.getQuyetDinh(), x.getLydoTuChoi());
                }).collect(Collectors.toList());
    }
    public List<com.example.demo.dto.response.NghiViecDto> GetAll() {
        return nghiViecRepository.findAll()
                .stream()
                .map(
                        x -> {
                            NhanVien nhanVien = x.getNhanVien();
                            return new com.example.demo.dto.response.NghiViecDto(x.getId(), nhanVien.getId(), nhanVien.getHoten(), x.getTungay(), x.getDenngay(), x.getLyDo(), x.getQuyetDinh(),x.getLydoTuChoi());
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
        return new NghiViecDto(nghiViec.getId(), nghiViec.getNhanVien().getId(), nghiViec.getNhanVien().getHoten(), nghiViec.getTungay(), nghiViec.getDenngay(), nghiViec.getLyDo(), nghiViec.getQuyetDinh(),"");

    }

    public NghiViecDto approveNghiViec(Long nghiViecId) {
        NghiViec nghiViec = nghiViecRepository.findById(nghiViecId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn nghỉ việc"));

        // 1. Cập nhật trạng thái đơn nghỉ việc
        nghiViec.setQuyetDinh("Từ chối");
        nghiViec.setLydoTuChoi("");
        nghiViecRepository.save(nghiViec);
        // 2. Cập nhật bảng chấm công cho các ngày nghỉ
        NhanVien nv = nghiViec.getNhanVien();
        LocalDate tuNgay = nghiViec.getTungay();
        LocalDate denNgay = nghiViec.getDenngay();

        return new NghiViecDto(
                nghiViec.getId(),
                nv.getId(),
                nv.getHoten(),
                nghiViec.getTungay(),
                nghiViec.getDenngay(),
                nghiViec.getLyDo(),
                nghiViec.getQuyetDinh(),
                nghiViec.getLydoTuChoi()
        );
    }

    public DeclineNghiviec declineNghiViec(Long nghiViecId, String liDoTuChoi) {
        NghiViec nghiViec = nghiViecRepository.findById(nghiViecId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn nghỉ việc"));

        // 1. Cập nhật trạng thái đơn nghỉ việc
        nghiViec.setQuyetDinh("Từ chối");
        nghiViec.setLydoTuChoi(liDoTuChoi);
        nghiViecRepository.save(nghiViec);
        // 2. Cập nhật bảng chấm công cho các ngày nghỉ
        NhanVien nv = nghiViec.getNhanVien();
        LocalDate tuNgay = nghiViec.getTungay();
        LocalDate denNgay = nghiViec.getDenngay();

        return new DeclineNghiviec(
                nghiViec.getId(),
                nv.getId(),
                nv.getHoten(),
                nghiViec.getTungay(),
                nghiViec.getDenngay(),
                nghiViec.getLyDo(),
                nghiViec.getQuyetDinh(),
                liDoTuChoi
        );
    }
}
