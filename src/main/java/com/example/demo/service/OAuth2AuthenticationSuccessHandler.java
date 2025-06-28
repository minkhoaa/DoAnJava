package com.example.demo.service;

import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.entity.NhanVien;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.NhanVienRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserRepository userRepository; // Thêm repository user của bạn
    private final NhanVienRepository nhanVienRepository;
    private final RoleRepository roleRepository;
    @Autowired
    public OAuth2AuthenticationSuccessHandler(JwtService jwtService,
                                              UserRepository userRepository,
                                              NhanVienRepository nhanVienRepository
    , RoleRepository roleRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauth2User = token.getPrincipal();

        // Lấy thông tin từ Google
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String avatar = oauth2User.getAttribute("picture");

        // Tìm hoặc tạo user trong database
        User user = userRepository.findByUsername(email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUsername(email);
                    newUser.setEmail(email);
                    newUser.setPasswordhash("");
                    // ... các trường khác tùy ý
                    userRepository.save(newUser);
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setHoten(name);
                    nhanVien.setNgaysinh(LocalDate.of(1970,1,1));
                    nhanVienRepository.save(nhanVien);
                    newUser.setEmployee(nhanVien);
                    userRepository.save(newUser);
                    // Gán quyền
                    Set<Role> roles = new HashSet<>();
                    Role userRole = roleRepository.findByRolename("User")
                            .orElseGet(() -> {
                                Role newRole = new Role();
                                newRole.setRolename("User");
                                return roleRepository.save(newRole);
                            });
                    roles.add(userRole);
                    newUser.setRoles(roles);

                    userRepository.save(newUser);
                    return newUser;
                });

        // Sinh JWT
        String jwt = jwtService.generateToken(new CustomUserDetails(user));

        // Trả về trang HTML có script gửi JWT về main window rồi tự đóng popup
        String html = "<html><head><meta charset='utf-8'></head><body>"
                + "<script>"
                + "if (window.opener) {"
                + "  window.opener.postMessage({type: 'google-auth-token', token: '" + jwt + "'}, '*');"
                + "  window.close();"
                + "} else {"
                + "  document.body.innerHTML = 'Vui lòng đóng cửa sổ này! +';"
                + "}"
                + "</script>"
                + "<p>Đang xử lý đăng nhập, vui lòng chờ...</p></body></html>"
                + "<p>Token: </p></body></html>" + jwt  ;
                ;
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(html);
    }
}
