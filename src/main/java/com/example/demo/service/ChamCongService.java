package com.example.demo.service;

import com.example.demo.dto.request.CheckInDto;
import com.example.demo.dto.request.TokenRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.ChamCong;
import com.example.demo.entity.LoaiCong;
import com.example.demo.entity.NhanVien;
import com.example.demo.repository.ChamCongRepository;
import com.example.demo.repository.LoaiCongRepository;
import com.example.demo.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.LocalDate;

@Service
public class ChamCongService  {
	@Autowired
	private ChamCongRepository chamCongRepository;
	@Autowired
	private AuthService authService;
	@Autowired
	private NhanVienRepository nhanVienRepository;
	@Autowired
	private LoaiCongRepository loaiCongRepository;

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

	public ApiResponse checkIn(CheckInDto checkInDto) {
		UserResponse authen = authService.authenticate(new TokenRequest(checkInDto.token));

		if (authen == null) return new ApiResponse("Unauthorized", null);

		NhanVien nhanVien = nhanVienRepository.findById(checkInDto.getEmployeeId()).orElse(null);
		if (nhanVien == null) return new ApiResponse("Not Found", null);
		double sogiolam = Duration.between(checkInDto.getGioVao(), checkInDto.getGioRa()).toMinutes() / 60.0;
		ChamCong chamCong = new ChamCong();
		chamCong.setEmployeeId(checkInDto.getEmployeeId());
		chamCong.setNgay(checkInDto.getTimeStamp());
		chamCong.setGioVao(checkInDto.getGioVao());
		chamCong.setGioRa(checkInDto.getGioRa());
		chamCong.setSoGioLam(sogiolam);
		chamCongRepository.save(chamCong);

		String tenLoaiCong = (sogiolam >= 8) ? "Đi làm bình thường" : "Làm thiếu giờ";
		double heso = (sogiolam >= 8) ? 1.0 : 0.5;

		LoaiCong loaiCong = new LoaiCong();
		loaiCong.setMachamcong(chamCong.getId());
		loaiCong.setTenLc(tenLoaiCong);
		loaiCong.setHeSo(heso);
		loaiCongRepository.save(loaiCong);

		return new ApiResponse("Success", chamCong);

	}


}
