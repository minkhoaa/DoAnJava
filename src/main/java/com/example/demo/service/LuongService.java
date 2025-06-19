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

        List<ChamCong> chamCongList =
                chamCongRepository.findByEmployeeIdAndThangAndNam(dto.getEmployeeId(), dto.getThang(), dto.getNam());

        double donGiaPhuCapMoiGio = 50000;
        double tongSoGioLam = 0;
        BigDecimal total = BigDecimal.ZERO;
        for (ChamCong c : chamCongList) {
            double heSo = 1.0;
            for (LoaiCong lc : loaiCongRepository.findByMachamcong(c.getId())) {
                if (lc.getHeSo() != null) {
                    heSo = lc.getHeSo();
                    break;
                }
            }
            double hours = c.getSoGioLam() != null ? c.getSoGioLam() : 0;
            tongSoGioLam += hours;
            BigDecimal salary = BigDecimal.valueOf(hours).multiply(BigDecimal.valueOf(1000000)).multiply(BigDecimal.valueOf(heSo));
            total = total.add(salary);
        }

        // Tính phụ cấp theo tổng số giờ làm việc
        BigDecimal phuCap = BigDecimal.valueOf(tongSoGioLam * donGiaPhuCapMoiGio);

        // Tính bảo hiểm, ví dụ 8% lương cơ bản
        BigDecimal baoHiem = total.multiply(BigDecimal.valueOf(0.08));

        // Tổng thu nhập thực
        BigDecimal thuNhapThuc = total.add(phuCap).subtract(baoHiem);

        NhanVien nv = nhanVienRepository.findById(dto.getEmployeeId()).orElse(null);

        Luong luong = new Luong();
        luong.setNhanVien(nv);
        luong.setThang(dto.getThang());
        luong.setNam(dto.getNam());
        luong.setLuongCoBan(total);
        luong.setPhuCap(phuCap);
        luong.setBaoHiem(baoHiem);
        luong.setThuNhapThuc(thuNhapThuc);
        luongRepository.save(luong);

        LuongDto luongDto = new LuongDto(
                luong.getLuongId(),
                nv != null ? nv.getId() : null,
                nv != null ? nv.getHoten() : null,
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
