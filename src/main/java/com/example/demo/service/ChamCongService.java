package com.example.demo.service;

import com.example.demo.dto.request.ModifiedCheckIn_OutDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

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

	public ApiResponse checkIn() {
		ApiResponse response = authService.getCurrentUserInfo();
		if (response.getData() == null) return new ApiResponse("Không tìm thấy thông tin nhân viên", null);
		UserResponse user = (UserResponse) response.getData();
		Long employeeId = user.getEmployeeId();
		NhanVien nhanVien = nhanVienRepository.findById(employeeId).orElse(null);
		if (nhanVien == null) return new ApiResponse("Not Found", null);

		LocalDate today = LocalDate.now();
		if (!chamCongRepository.findByNgayAndEmployeeId(today, employeeId).isEmpty()) {
			return new ApiResponse("Đã check-in hôm nay", null);
		}

		ChamCong chamCong = new ChamCong();
		chamCong.setEmployeeId(employeeId);
		chamCong.setNgay(today);
		chamCong.setGioVao(LocalTime.now());

		chamCongRepository.save(chamCong);
		return new ApiResponse("Check-in thành công", chamCong);
	}
	public ApiResponse checkOut() {
		ApiResponse response = authService.getCurrentUserInfo();
		if (response.getData() == null) return new ApiResponse("Không tìm thấy thông tin nhân viên", null);
		UserResponse user = (UserResponse) response.getData();
		Long employeeId = user.getEmployeeId();
		LocalDate today = LocalDate.now();
		ChamCong chamCong = chamCongRepository.findByNgayAndEmployeeId(today, employeeId).stream().findFirst().orElse(null);

		if (chamCong == null) return new ApiResponse("Chưa check-in", null);
		if (chamCong.getGioRa() != null) return new ApiResponse("Đã check-out rồi", null);

		LocalTime gioRa = LocalTime.now();
		chamCong.setGioRa(gioRa);

		double sogiolam = Duration.between(chamCong.getGioVao(), gioRa).toMinutes() / 60.0;
		chamCong.setSoGioLam(sogiolam);
		chamCongRepository.save(chamCong);

		String tenLoaiCong = (sogiolam >= 8) ? "Đi làm bình thường" : "Làm thiếu giờ";
		double heso = (sogiolam >= 8) ? 1.0 : 0.5;

		LoaiCong loaiCong = new LoaiCong();
		loaiCong.setMachamcong(chamCong.getId());
		loaiCong.setTenLc(tenLoaiCong);
		loaiCong.setHeSo(heso);
		loaiCongRepository.save(loaiCong);

		return new ApiResponse("Check-out thành công", chamCong);
	}
	public ApiResponse ModifierChamCong(ModifiedCheckIn_OutDto dto) {
		UserResponse authen = authService.authenticate(new TokenRequest(dto.getToken()));
		if (authen == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
		Optional<ChamCong> chamCongOptional = chamCongRepository.findById(dto.getId());
		if (!chamCongOptional.isPresent()) return new ApiResponse("Not Found", null);
		ChamCong chamCong = chamCongOptional.get();
		chamCong.setGioVao(dto.getGioVao());
		chamCong.setGioRa(dto.getGioRa());
		chamCongRepository.save(chamCong);
		return new ApiResponse("Success", chamCong);
	}



}
