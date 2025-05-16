package com.example.demo.service;

import com.example.demo.dto.request.PhieuLuongInputDto;
import com.example.demo.dto.request.TokenRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.Luong;
import com.example.demo.repository.LuongRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LuongService {
    @Autowired
    private LuongRepository luongRepository;
    @Autowired
    private AuthService authService;

    public ApiResponse getFilteredPhieuLuong(PhieuLuongInputDto dto) {
        UserResponse user = authService.authenticate(new TokenRequest(dto.getToken()));
        if (user == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");

        Long employeeId = dto.getEmployeeId();
        Integer thang = dto.getThang();
        Integer nam = dto.getNam();
        List<Luong> result;
        if (employeeId != null && thang != null && nam != null) {
            result = luongRepository.findByNhanVien_IdAndThangAndNam(employeeId, thang, nam);
        } else if (employeeId != null && thang != null) {
            result = luongRepository.findByNhanVien_IdAndThang(employeeId, thang);
        } else if (employeeId != null && nam != null) {
            result = luongRepository.findByNhanVien_IdAndNam(employeeId, nam);
        } else if (thang != null && nam != null) {
            result = luongRepository.findByNamAndThang(nam, thang);
        } else if (employeeId != null) {
            result = luongRepository.findByNhanVien_Id(employeeId);
        } else if (thang != null) {
            result = luongRepository.findByThang(thang);
        } else if (nam != null) {
            result = luongRepository.findByNam(nam);
        } else {
            result = luongRepository.findAll(Sort.by(Sort.Direction.ASC, "nhanVien.id"));
        }

        return new ApiResponse("Success", result);
    }
    public ApiResponse getAllPhieuLuong(String token) {
        UserResponse user = authService.authenticate(new TokenRequest(token));
        if (user == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        return new ApiResponse("Success", luongRepository.findAll(Sort.by(Sort.Direction.ASC, "nhanVien.id")));
    }

}
