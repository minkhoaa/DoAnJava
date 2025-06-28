package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.TrinhDoDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.TrinhDo;
import com.example.demo.repository.TrinhDoRepository;

import java.util.Optional;

@Service
public class TrinhDoService {

    @Autowired
    private TrinhDoRepository trinhDoRepository;

    public ApiResponse getAllTrinhDo() {
        return new ApiResponse("Success", trinhDoRepository.findAll());
    }

    public ApiResponse getTrinhDoById(Long id) {
        Optional<TrinhDo> trinhDo = trinhDoRepository.findById(id);
        if (!trinhDo.isPresent())
            return new ApiResponse("failed", "Không tồn tại trình độ này");

        return new ApiResponse("Success", trinhDo.get());
    }

    public ApiResponse addTrinhDo(TrinhDoDto trinhDoDto) {
        if (trinhDoDto.getName() == null)
            return new ApiResponse("failed", "Tên trình độ không được để trống");

        TrinhDo trinhDo = new TrinhDo();
        trinhDo.setName(trinhDoDto.getName());
        return new ApiResponse("Success", trinhDoRepository.save(trinhDo));
    }

    public ApiResponse updateTrinhDo(Long id, TrinhDoDto trinhDoDto) {
        Optional<TrinhDo> trinhDoOptional = trinhDoRepository.findById(id);
        if (!trinhDoOptional.isPresent())
            return new ApiResponse("failed", "Không tồn tại trình độ này");

        TrinhDo trinhDo = trinhDoOptional.get();
        if (trinhDoDto.getName() != null) {
            trinhDo.setName(trinhDoDto.getName());
        }

        return new ApiResponse("Success", trinhDoRepository.save(trinhDo));
    }

    public ApiResponse deleteTrinhDo(Long id) {
        Optional<TrinhDo> trinhDoOptional = trinhDoRepository.findById(id);
        if (!trinhDoOptional.isPresent())
            return new ApiResponse("failed", "Không tồn tại trình độ này");

        trinhDoRepository.deleteById(id);
        return new ApiResponse("Success", "Xóa thành công: " + trinhDoOptional.get());
    }
}