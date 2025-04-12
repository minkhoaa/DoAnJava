package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.HopDongDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.HopDong;
import com.example.demo.entity.NhanVien;
import com.example.demo.repository.HopDongRepository;
import com.example.demo.repository.NhanVienRepository;

import java.util.Optional;

@Service
public class HopDongService {

    @Autowired
    private HopDongRepository hopDongRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    // Lấy tất cả hợp đồng
    public ApiResponse getAllHopDong() {
        return new ApiResponse("Success", hopDongRepository.findAll());
    }

    // Lấy hợp đồng theo số hợp đồng
    public ApiResponse getHopDongById(Long sohd) {
        Optional<HopDong> hopDong = hopDongRepository.findById(sohd);
        if (!hopDong.isPresent())
            return new ApiResponse("failed", "Không tồn tại hợp đồng này");

        return new ApiResponse("Success", hopDong.get());
    }

    // Thêm mới hợp đồng
    public ApiResponse addHopDong(HopDongDto hopDongDto) {
        Optional<NhanVien> nhanVienOptional = nhanVienRepository.findById(hopDongDto.getManv());
        if (!nhanVienOptional.isPresent())
            return new ApiResponse("failed", "Không tồn tại nhân viên với mã này");

        HopDong hopDong = new HopDong();
        hopDong.setNgayBatDau(hopDongDto.getNgayBatDau());
        hopDong.setNgayKetThuc(hopDongDto.getNgayKetThuc());
        hopDong.setNgayKy(hopDongDto.getNgayKy());
        hopDong.setNoiDung(hopDongDto.getNoiDung());
        hopDong.setNhanVien(nhanVienOptional.get());

        return new ApiResponse("Success", hopDongRepository.save(hopDong));
    }

    // Cập nhật hợp đồng
    public ApiResponse updateHopDong(Long sohd, HopDongDto hopDongDto) {
        Optional<HopDong> hopDongOptional = hopDongRepository.findById(sohd);
        if (!hopDongOptional.isPresent())
            return new ApiResponse("failed", "Không tồn tại hợp đồng này");

        HopDong hopDong = hopDongOptional.get();
        if (hopDongDto.getNgayBatDau() != null) {
            hopDong.setNgayBatDau(hopDongDto.getNgayBatDau());
        }
        if (hopDongDto.getNgayKetThuc() != null) {
            hopDong.setNgayKetThuc(hopDongDto.getNgayKetThuc());
        }
        if (hopDongDto.getNgayKy() != null) {
            hopDong.setNgayKy(hopDongDto.getNgayKy());
        }
        if (hopDongDto.getNoiDung() != null) {
            hopDong.setNoiDung(hopDongDto.getNoiDung());
        }
        if (hopDongDto.getManv() != null) {
            Optional<NhanVien> nhanVienOptional = nhanVienRepository.findById(hopDongDto.getManv());
            if (!nhanVienOptional.isPresent())
                return new ApiResponse("failed", "Không tồn tại nhân viên với mã này");
            hopDong.setNhanVien(nhanVienOptional.get());
        }

        return new ApiResponse("Success", hopDongRepository.save(hopDong));
    }

    // Xóa hợp đồng
    public ApiResponse deleteHopDong(Long sohd) {
        Optional<HopDong> hopDongOptional = hopDongRepository.findById(sohd);
        if (!hopDongOptional.isPresent())
            return new ApiResponse("failed", "Không tồn tại hợp đồng này");

        hopDongRepository.deleteById(sohd);
        return new ApiResponse("Success", "Xóa thành công: " + hopDongOptional.get());
    }
}
