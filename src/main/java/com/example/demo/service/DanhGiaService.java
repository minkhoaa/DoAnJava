package com.example.demo.service;

import com.example.demo.dto.request.AddDanhGiaDto;
import com.example.demo.dto.request.DanhGiaInputDto;
import com.example.demo.dto.request.TokenRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.DanhGia;
import com.example.demo.entity.NhanVien;
import com.example.demo.repository.DanhGiaRepository;
import com.example.demo.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
public class DanhGiaService {
    @Autowired
    private AuthService authService;
    @Autowired
    private DanhGiaRepository danhGiaRepository;
    @Autowired
    private NhanVienRepository nhanVienRepository;

    public ApiResponse getAllDanhGia(DanhGiaInputDto danhGiaInputDto) {
        UserResponse user = authService.authenticate(new TokenRequest(danhGiaInputDto.getToken()));
        if (user == null) throw new ResponseStatusException(UNAUTHORIZED, "Invalid token");

        Long employeeId = danhGiaInputDto.getEmployeeId();
        Integer ky = danhGiaInputDto.getKy();
        Integer nam = danhGiaInputDto.getNam();
        List<DanhGia> danhGiaList ;
        if (employeeId != null && ky != null && nam != null ) {
            danhGiaList = danhGiaRepository.findByNhanVien_IdAndKyAndNam(employeeId, ky, nam );
        }
        else if (employeeId != null && ky != null) {
            danhGiaList = danhGiaRepository.findByNhanVien_IdAndKy(employeeId, ky );
        }
        else if (employeeId != null && nam != null) {
            danhGiaList = danhGiaRepository.findByNhanVien_IdAndNam(employeeId, nam );
        }
        else if (ky != null && nam != null) {
            danhGiaList = danhGiaRepository.findByKyAndNam(ky, nam );
        }

        else if (employeeId != null) {
            danhGiaList = danhGiaRepository.findByNhanVien_Id(employeeId);
        }
        else if (ky != null) {
            danhGiaList = danhGiaRepository.findByKy(ky);
        }
        else if (nam != null) {
            danhGiaList = danhGiaRepository.findByNam(nam);
        }
        else {
            danhGiaList = danhGiaRepository.findAll();
        }

        return new ApiResponse("Success", danhGiaList);
    }

    public ApiResponse addDanhGia(AddDanhGiaDto dto) {
        UserResponse user = authService.authenticate(new TokenRequest(dto.getToken()) );
        if (user == null) throw new ResponseStatusException(UNAUTHORIZED, "Invalid token");

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

