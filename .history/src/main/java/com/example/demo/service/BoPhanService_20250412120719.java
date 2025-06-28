package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.request.BoPhanDto;
import com.example.demo.entity.BoPhan;
import com.example.demo.repository.BoPhanRepository;

@Service
public class BoPhanService {
    @Autowired
    private BoPhanRepository boPhanRepository;

    // Lấy tất cả bộ phận
    public ApiResponse getAllBoPhan() {
        return new ApiResponse("Success", boPhanRepository.findAll());
    }

    // Lấy bộ phận theo ID
    public ApiResponse getBoPhanById(Long id) {
        Optional<BoPhan> boPhan = boPhanRepository.findById(id);
        if (!boPhan.isPresent())
            return new ApiResponse("failed", "Không tồn tại bộ phận này");

        return new ApiResponse("Success", boPhan.get());
    }

    // Thêm mới bộ phận
    public ApiResponse addBoPhan(BoPhanDto boPhanDto) {
        if (boPhanDto.getName() == null)
            return new ApiResponse("failed", "Tên bộ phận không được để trống");

        BoPhan boPhan = new BoPhan();
        try {
            boPhan.setName(boPhanDto.getName());
            return new ApiResponse("Success", boPhanRepository.save(boPhan));
        } catch (Exception ex) {
            return new ApiResponse("failed", ex.getMessage());
        }
    }

    // Cập nhật thông tin bộ phận
    public ApiResponse updateBoPhan(Long id, BoPhanDto boPhanDto) {
        Optional<BoPhan> boPhanOptional = boPhanRepository.findById(id);
        if (!boPhanOptional.isPresent())
            return new ApiResponse("failed", "Không tồn tại bộ phận này");

        BoPhan boPhan = boPhanOptional.get();
        if (boPhanDto.getName() != null) {
            boPhan.setName(boPhanDto.getName());
        }

        return new ApiResponse("Success", boPhanRepository.save(boPhan));
    }

    // Xóa bộ phận
    public ApiResponse deleteBoPhan(Long id) {
        Optional<BoPhan> boPhanOptional = boPhanRepository.findById(id);
        if (!boPhanOptional.isPresent())
            return new ApiResponse("failed", "Không tồn tại bộ phận này");

        boPhanRepository.deleteById(id);
        return new ApiResponse("Success", "Xóa thành công: " + boPhanOptional.get());
    }
}
