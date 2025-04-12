package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.ChucVuDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.ChucVu;
import com.example.demo.repository.ChucVuRepository;

import java.util.Optional;

@Service
public class ChucVuService {

    @Autowired
    private ChucVuRepository chucVuRepository;

    // Lấy tất cả chức vụ
    public ApiResponse getAllChucVu() {
        return new ApiResponse("Success", chucVuRepository.findAll());
    }

    // Lấy chức vụ theo ID
    public ApiResponse getChucVuById(Long id) {
        Optional<ChucVu> chucVu = chucVuRepository.findById(id);
        if (!chucVu.isPresent())
            return new ApiResponse("failed", "Không tồn tại chức vụ này");

        return new ApiResponse("Success", chucVu.get());
    }

    // Thêm mới chức vụ
    public ApiResponse addChucVu(ChucVuDto chucVuDto) {
        if (chucVuDto.getName() == null)
            return new ApiResponse("failed", "Tên chức vụ không được để trống");

        ChucVu chucVu = new ChucVu();
        chucVu.setName(chucVuDto.getName());
        return new ApiResponse("Success", chucVuRepository.save(chucVu));
    }

    // Cập nhật chức vụ
    public ApiResponse updateChucVu(Long id, ChucVuDto chucVuDto) {
        Optional<ChucVu> chucVuOptional = chucVuRepository.findById(id);
        if (!chucVuOptional.isPresent())
            return new ApiResponse("failed", "Không tồn tại chức vụ này");

        ChucVu chucVu = chucVuOptional.get();
        if (chucVuDto.getName() != null) {
            chucVu.setName(chucVuDto.getName());
        }

        return new ApiResponse("Success", chucVuRepository.save(chucVu));
    }

    // Xóa chức vụ
    public ApiResponse deleteChucVu(Long id) {
        Optional<ChucVu> chucVuOptional = chucVuRepository.findById(id);
        if (!chucVuOptional.isPresent())
            return new ApiResponse("failed", "Không tồn tại chức vụ này");

        chucVuRepository.deleteById(id);
        return new ApiResponse("Success", "Xóa thành công: " + chucVuOptional.get());
    }
}
