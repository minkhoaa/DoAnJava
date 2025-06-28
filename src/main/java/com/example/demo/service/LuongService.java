package com.example.demo.service;

import com.example.demo.dto.request.PhieuLuongInputDto;
import com.example.demo.dto.request.TokenRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.LuongDto;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LuongService {
    @Autowired
    private LuongRepository luongRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private  ChamCongRepository chamCongRepository;
    @Autowired
    private LoaiCongRepository loaiCongRepository;
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private BaoHiemRepository baoHiemRepository;



    public ApiResponse getAllPhieuLuong() {
        List<Luong> luongs = luongRepository.findAll(Sort.by(Sort.Direction.ASC, "nhanVien.id"));

        // Map entity sang DTO
        List<LuongDto> dtoList = luongs.stream().map(luong -> new LuongDto(
                luong.getLuongId(),
                luong.getNhanVien() != null ? luong.getNhanVien().getId() : null,
                luong.getNhanVien() != null ? luong.getNhanVien().getHoten() : null,
                luong.getThang(),
                luong.getNam(),
                luong.getLuongCoBan(),
                luong.getPhuCap(),
                luong.getBaoHiem(),
                luong.getThuNhapThuc()
        )).collect(Collectors.toList());

        return new ApiResponse("Success", dtoList);
    }
    public ApiResponse calculateLuong(PhieuLuongInputDto dto) {
        if (dto.getEmployeeId() == null || dto.getThang() == null || dto.getNam() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing input");
        }

        // Lấy thông tin nhân viên
        NhanVien nv = nhanVienRepository.findById(dto.getEmployeeId()).orElse(null);
        if (nv == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy nhân viên");
        }

        String chucVu = nv.getChucVu().getName().trim();

        BigDecimal luongCung;
        switch (chucVu) {
            case "Giám đốc":
                luongCung = BigDecimal.valueOf(20000000);
                break;
            case "Trưởng phòng":
                luongCung = BigDecimal.valueOf(10000000);
                break;
            case "Kế toán":
                luongCung = BigDecimal.valueOf(8000000);
                break;
            default: // Nhân viên và các chức vụ khác
                luongCung = BigDecimal.valueOf(6000000);
        }

        int soNgayCongTieuChuan = 26;
        int soNgayLam = 0;
        int soNgayThieuCong = 0;

        List<ChamCong> chamCongList = chamCongRepository.findByEmployeeIdAndThangAndNam(
                dto.getEmployeeId(), dto.getThang(), dto.getNam());

        for (ChamCong c : chamCongList) {
            soNgayLam++;
            LoaiCong loaiCong = loaiCongRepository.findByMachamcong(c.getId());
            String tenLoaiCong = loaiCong != null && loaiCong.getTenLc() != null ? loaiCong.getTenLc().trim() : "";
            if ("Làm thiếu giờ".equalsIgnoreCase(tenLoaiCong)) {
                soNgayThieuCong++;
            }
        }

        BigDecimal tongPhat = BigDecimal.valueOf(soNgayThieuCong * 100000);
        BigDecimal phuCap = BigDecimal.valueOf(soNgayLam * 100000);

        // Tất cả chức vụ đều phải đủ công mới nhận full lương cứng, thiếu thì chia theo ngày thực tế
        BigDecimal tongLuongCoBan;
        if (soNgayLam >= soNgayCongTieuChuan) {
            tongLuongCoBan = luongCung;
        } else {
            tongLuongCoBan = luongCung
                    .divide(BigDecimal.valueOf(soNgayCongTieuChuan), 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(soNgayLam));
        }

        BigDecimal tongLuongTruocBH = tongLuongCoBan.subtract(tongPhat);
        BigDecimal baoHiem = luongCung.multiply(BigDecimal.valueOf(0.08));
        BigDecimal thuNhapThuc = tongLuongTruocBH.add(phuCap).subtract(baoHiem);
        if (thuNhapThuc.compareTo(BigDecimal.ZERO) < 0) {
            thuNhapThuc = BigDecimal.ZERO;
        }
        Luong luong = new Luong();
        luong.setNhanVien(nv);
        luong.setThang(dto.getThang());
        luong.setNam(dto.getNam());
        luong.setLuongCoBan(tongLuongCoBan);
        luong.setPhuCap(phuCap);
        luong.setBaoHiem(baoHiem);
        luong.setThuNhapThuc(thuNhapThuc);
        luongRepository.save(luong);

        LuongDto luongDto = new LuongDto(
                luong.getLuongId(),
                nv.getId(),
                nv.getHoten(),
                luong.getThang(),
                luong.getNam(),
                luong.getLuongCoBan(),
                luong.getPhuCap(),
                luong.getBaoHiem(),
                luong.getThuNhapThuc()
        );

        return new ApiResponse("Success", luongDto);
    }



}
