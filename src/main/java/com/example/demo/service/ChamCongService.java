package com.example.demo.service;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.ChamCong;
import com.example.demo.repository.ChamCongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
@Service
public class ChamCongService {
	@Autowired
	private ChamCongRepository chamCongRepository;


	public ApiResponse getAllChamCong(  @RequestParam(value = "ngay", required = false)
										@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ngay,

										@RequestParam(value = "employId", required = false) Long employId) {

			if (ngay != null && employId != null) {
				return new ApiResponse("", chamCongRepository.findByNgayAndEmployeeId(ngay, employId));
			}
			else if (employId != null ) {
				return new ApiResponse("", chamCongRepository.findByEmployeeId(employId));
		}
			else if (ngay != null) {
				return new ApiResponse("", chamCongRepository.findByNgay(ngay));
		}
			return new ApiResponse("", chamCongRepository.findAll());
	}
}
