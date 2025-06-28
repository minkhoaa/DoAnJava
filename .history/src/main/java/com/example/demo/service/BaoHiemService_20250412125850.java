package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.BaoHiemDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.BaoHiem;
import com.example.demo.entity.NhanVien;
import com.example.demo.repository.BaoHiemRepository;
import com.example.demo.repository.NhanVienRepository;

import java.util.Optional;

@Service
public class BaoHiemService {

    @Autowired
    private BaoHiemRepository baoHiemRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    // Lấy tất cả bảo hiểm
    public ApiResponse getAllBaoHiem() {
        return new ApiResponse("Success", baoHiemRepository.findAll());
    }

    // Lấy bảo hiểm theo ID
    public ApiResponse getBaoHiemById(Long idbh) {
        Optional<BaoHiem> baoHiem = baoHiemRepository.findById(idbh);
        if (!baoHiem.isPresent())
            return new ApiResponse("failed", "Không tồn tại bảo hiểm này");

        return new ApiResponse("Success", baoHiem.get());
    }

    // Thêm mới bảo hiểm
    public ApiResponse addBaoHiem(BaoHiemDto baoHiemDto) {
        Optional<NhanVien> nhanVienOptional = nhanVienRepository.findById(baoHiemDto.getManv());
        if (!nhanVienOptional.isPresent())
            return new ApiResponse("failed", "Không tồn tại nhân viên với mã này");

        BaoHiem baoHiem = new BaoHiem();
        baoHiem.setSobh(baoHiemDto.getSobh());
        baoHiem.setNgayCap(baoHiemDto.getNgayCap());
        baoHiem.setNoiCap(baoHiemDto.getNoiCap());
        baoHiem.setNoiKhamBenh(baoHiemDto.getNoiKhamBenh());
        baoHiem.setNhanVien(nhanVienOptional.get());

        return new ApiResponse("Success", baoHiemRepository.save(baoHiem));
    }

    // Cập nhật bảo hiểm
    public ApiResponse updateBaoHiem(Long idbh, BaoHiemDto baoHiemDto) {
        Optional<BaoHiem> baoHiemOptional = baoHiemRepository.findById(idbh);
        if (!baoHiemOptional.isPresent())
            return new ApiResponse("failed", "Không tồn tại bảo hiểm này");

        BaoHiem baoHiem = baoHiemOptional.get();
        if (baoHiemDto.getSobh() != null) {
            baoHiem.setSobh(baoHiemDto.getSobh());
        }
        if (baoHiemDto.getNgayCap() != null) {
            baoHiem.setNgayCap(baoHiemDto.getNgayCap());
        }
        if (baoHiemDto.getNoiCap() != null) {
            baoHiem.setNoiCap(baoHiemDto.getNoiCap());
        }
        if (baoHiemDto.getNoiKhamBenh() != null) {
            baoHiem.setNoiKhamBenh(baoHiemDto.getNoiKhamBenh());
        }
        if (baoHiemDto.getManv() != null) {
            Optional<NhanVien> nhanVienOptional = nhanVienRepository.findById(baoHiemDto.getManv());
            if (!nhanVienOptional.isPresent())
                return new ApiResponse("failed", "Không tồn tại nhân viên với mã này");
            baoHiem.setNhanVien(nhanVienOptional.get());
        }

        return new ApiResponse("Success", baoHiemRepository.save(baoHiem));
    }

    // Xóa bảo hiểm
    public ApiResponse deleteBaoHiem(Long idbh) {
        Optional<BaoHiem> baoHiemOptional = baoHiemRepository.findById(idbh);
        if (!baoHiemOptional.isPresent())
            return new ApiResponse("failed", "Không tồn tại bảo hiểm này");

        baoHiemRepository.deleteById(idbh);
        return new ApiResponse("Success", "Xóa thành công: " + baoHiemOptional.get());
    }
}