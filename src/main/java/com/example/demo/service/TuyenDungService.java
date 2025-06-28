package com.example.demo.service;

import com.example.demo.dto.request.CandidateByJobIdDto;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.request.TokenRequest;
import com.example.demo.dto.request.TuyenDungInputDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.NhanVien;
import com.example.demo.entity.TuyenDung;
import com.example.demo.entity.User;
import com.example.demo.repository.NhanVienRepository;
import com.example.demo.repository.TuyenDungRepository;
import com.example.demo.repository.UserRepository;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.tree.ExpandVetoException;
import java.util.Map;

@Service
public class TuyenDungService {
    @Autowired
    private TuyenDungRepository tuyenDungRepository;
    @Autowired
    private NhanVienRepository nhanVienRepository; // Giả sử bạn có entity NhanVien
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository; // Entity User để quản lý tài khoản đăng nhập

    private AuthenticationManager authenticationManager;

    public ApiResponse getAllTuyenDungDangXetDuyet() {


        return new ApiResponse("Success", tuyenDungRepository.findByTrangthai("Đang xét duyệt"));

    }
    public ApiResponse getCandidatesByJobId(CandidateByJobIdDto candidateByJobIdDto) {
   return new ApiResponse("Success",tuyenDungRepository.findById(candidateByJobIdDto.getJobId()));
    }

    public ApiResponse addNewTuyenDung(TuyenDungInputDto tuyenDungInputDto) {

        TuyenDung tuyenDung = new TuyenDung();
        tuyenDung.setHoten(tuyenDungInputDto.getHoten());
        tuyenDung.setNgaysinh(tuyenDungInputDto.getNgaysinh());
        tuyenDung.setEmail(tuyenDungInputDto.getEmail());
        tuyenDung.setDienthoai(tuyenDungInputDto.getDienthoai());
        tuyenDung.setVitri(tuyenDungInputDto.getVitri());
        tuyenDung.setTrangthai("Đang xét duyệt");
        tuyenDungRepository.save(tuyenDung);
        return new ApiResponse("Success", tuyenDung);

    }
    public ApiResponse acceptCandidate(Long candidateId) {
        // 1. Tìm ứng viên theo id
        TuyenDung candidate = tuyenDungRepository.findById(candidateId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy ứng viên"));

        // 2. Kiểm tra ứng viên đã có user chưa
        if (userRepository.findByUsername(candidate.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ứng viên này đã có tài khoản!");
        }

        // 3. Sinh mật khẩu random (nên bảo mật hơn số điện thoại)
        String tempPassword = generateRandomPassword();

        // 4. Tạo RegisterRequest với thông tin đầy đủ
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername(candidate.getEmail());
        registerRequest.setPassword(tempPassword);
        registerRequest.setEmail(candidate.getEmail());
        registerRequest.setFirstName(""); // Tách họ nếu muốn, hoặc để trống
        registerRequest.setLastName(candidate.getHoten());
        registerRequest.setPhoneNumber(candidate.getDienthoai());
        // Nếu muốn set ngày sinh, bổ sung trường và truyền luôn:
        // registerRequest.setDob(candidate.getNgaysinh());

        AuthenticationResponse authentication = authService.register(registerRequest);

        // 5. Cập nhật trạng thái ứng viên
        candidate.setTrangthai("Đã tuyển dụng");
        tuyenDungRepository.save(candidate);

        // 6. Lấy nhân viên vừa tạo từ user
        var user = userRepository.findByUsername(candidate.getEmail());
        NhanVien nhanVien = user.isPresent() ? user.get().getEmployee() : null;

        // 7. (Có thể gửi email cho nhân viên mới tại đây...)

        // 8. Trả về thông tin đăng nhập cho admin (hoặc gửi email cho nhân viên mới)
        return new ApiResponse("Duyệt ứng viên thành công & đã cấp tài khoản",
                Map.of(
                        "nhanVien", nhanVien,
                        "username", candidate.getEmail(),
                        "tempPassword", tempPassword
                )
        );
    }

    // Hàm sinh mật khẩu random (có thể chuyển thành private static nếu muốn)
    private String generateRandomPassword() {
        // Nếu chưa import, dùng org.apache.commons.lang3.RandomStringUtils
        return org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(8);
    }



}
