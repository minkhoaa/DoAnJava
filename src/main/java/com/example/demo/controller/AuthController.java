package com.example.demo.controller;

import com.example.demo.dto.request.TokenRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthService authService;
    
    @Autowired
    private final UserRepository user;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
// luôn truyền vào hasRole('ADMIN') hoặc USER, không chứa chữ thường
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/authentication")
    public ResponseEntity<UserResponse> authentication(@RequestBody TokenRequest tokenRequest) {
        var result = authService.authenticate(tokenRequest);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/api/auth/getcurrentUserInfor")
    public ApiResponse getNhanVienFromToken() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attr != null) {
            String authHeader = attr.getRequest().getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                UserResponse user = authService.authenticate(new TokenRequest(token));
                if (user != null) {
                    return new ApiResponse("success", user);
                }
                return new ApiResponse("fail", "Token is invalid");
            }
        }
        return new ApiResponse("fail", "Token is missing");
    }


}