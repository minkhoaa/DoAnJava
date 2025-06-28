package com.example.demo.service;

import com.example.demo.dto.request.AddDanhGiaDto;
import com.example.demo.dto.response.NhanVienDto;
import com.example.demo.dto.response.DanhGiaDto;
import com.example.demo.entity.*;
import com.example.demo.repository.DanhGiaRepository;
import com.example.demo.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.response.ApiResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DanhGiaService {
    @Autowired
    private DanhGiaRepository danhGiaRepository;
    @Autowired
    private NhanVienRepository nhanVienRepository;

    public ApiResponse getAllDanhGia() {
        List<DanhGia> danhGiaList = danhGiaRepository.findAll();
        List<DanhGiaDto> response = danhGiaList.stream().map(c -> {
            NhanVien nv = c.getNhanVien();
            NhanVienDto nvDto = null;
            PhongBan phongBan = nv.getPhongBan();
            ChucVu chucVu = nv.getChucVu();
            TrinhDo trinhDo = nv.getTrinhDo();
            Long idcv = chucVu != null ? chucVu.getId() : null;
            String tenChucVu = chucVu != null ? chucVu.getName() : null;
            Long idpb = phongBan != null ? phongBan.getId() : null;
            String tenPhongBan = phongBan != null ? phongBan.getName() : null;
            Long idtd = trinhDo != null ? trinhDo.getId() : null;
            String tenTrinhDo = trinhDo != null ? trinhDo.getName() : null;
            nvDto = new NhanVienDto(
                    nv.getId(),
                    nv.getHoten(),
                    nv.getGioitinh(),
                    nv.getNgaysinh(),
                    nv.getDienthoai(),
                    nv.getCccd(),
                    nv.getDiachi(),
                    nv.getHinhanh(),
                    idcv,
                    tenChucVu,
                    idpb,
                    tenPhongBan,
                    idtd,
                    tenTrinhDo
            );
            DanhGiaDto dto = new DanhGiaDto(c.getId(), c.getDiemSo(), c.getKy(), c.getNam(),c.getNhanXet(), nvDto );
            return dto;
        }).collect(Collectors.toList());
        return new ApiResponse("Success", response );
    }
    public ApiResponse getDanhGiaById(Long id) {
        Optional<DanhGia> danhGiaOpt = danhGiaRepository.findById(id);

        if (danhGiaOpt.isEmpty()) {
            return new ApiResponse("Không tìm thấy đánh giá!", null);
        }

        DanhGia c = danhGiaOpt.get();
        NhanVien nv = c.getNhanVien();
        NhanVienDto nvDto = null;

        if (nv != null) {
            nvDto = new NhanVienDto(
                    nv.getId(),
                    nv.getHoten(),
                    nv.getGioitinh(),
                    nv.getNgaysinh(),
                    nv.getDienthoai(),
                    nv.getCccd(),
                    nv.getDiachi(),
                    nv.getHinhanh(),
                    nv.getChucVu() != null ? nv.getChucVu().getId() : null,
                    nv.getChucVu() != null ? nv.getChucVu().getName() : null,
                    nv.getPhongBan() != null ? nv.getPhongBan().getId() : null,
                    nv.getPhongBan() != null ? nv.getPhongBan().getName() : null,
                    nv.getTrinhDo() != null ? nv.getTrinhDo().getId() : null,
                    nv.getTrinhDo() != null ? nv.getTrinhDo().getName() : null
            );
        }

        DanhGiaDto dto = new DanhGiaDto(
                c.getId(),
                c.getDiemSo(),
                c.getKy(),
                c.getNam(),
                c.getNhanXet(),
                nvDto
        );

        return new ApiResponse("Success", dto);
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

