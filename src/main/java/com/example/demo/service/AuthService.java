package com.example.demo.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.dto.request.NhanVienDto;
import com.example.demo.dto.request.TokenRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.UserResponse;
import org.aspectj.weaver.patterns.ExactTypePattern;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.entity.NhanVien;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.NhanVienRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final NhanVienRepository nhanVienRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already in use");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPasswordhash(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        // Liên kết với nhân viên
        NhanVien nhanVien = new NhanVien();
        nhanVien.setHoten(request.getFirstName() + " " + request.getLastName());
        nhanVien.setDienthoai(request.getPhoneNumber());
        nhanVien.setNgaysinh(LocalDate.of(1970,1,1));
        nhanVienRepository.save(nhanVien);
        user.setEmployee(nhanVien);
        userRepository.save(user);
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByRolename("User")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setRolename("User");
                    return roleRepository.save(newRole);
                });
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
        String token = jwtService.generateToken(new CustomUserDetails(user));
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(new CustomUserDetails(user));
        return new AuthenticationResponse(token);
    }
    public UserResponse authenticate(TokenRequest tokenRequest) {
        String token = tokenRequest.getToken();

        if (token == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is missing");
        }

        // Xử lý token và thêm "Bearer " nếu chưa có
        String processedToken = token.startsWith("Bearer ") ? token : "Bearer " + token;

        String username;
        try {
            // Trích xuất username từ token gốc (không có Bearer)
            String actualToken = token.startsWith("Bearer ") ? token.substring(7) : token;
            username = jwtService.extractUsername(actualToken);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token", e);
        }

        // Kiểm tra xem người dùng có tồn tại trong cơ sở dữ liệu không
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));

        // Tạo đối tượng CustomUserDetails từ thông tin người dùng
        CustomUserDetails userDetails = new CustomUserDetails(user);

        // Kiểm tra tính hợp lệ của token (so sánh username và kiểm tra token hết hạn)
        boolean isValid = jwtService.isTokenValid(
                token.startsWith("Bearer ") ? token.substring(7) : token,
                userDetails
        );

        if (!isValid) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired token");
        }

        // Trả về AuthenticationResponse với token đã được xử lý
        return new UserResponse(user.getUserid(),user.getUsername(),user.getEmail(), user.getEmployee().getId());
    }


}
