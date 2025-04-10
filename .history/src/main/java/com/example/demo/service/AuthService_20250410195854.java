package com.example.demo.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

   public AuthenticationResponse register(RegisterRequest request) {
    if (userRepository.findByUsername(request.getUsername()).isPresent()) {
        throw new RuntimeException("Username already exists");
    }

    User user = new User();
    user.setUsername(request.getUsername());
    user.setPasswordhash(passwordEncoder.encode(request.getPassword()));
    user.setEmail(request.getEmail());
    user.(request.getEmployeeId());

    // Gán roles
    Set<Role> roles = request.getRoles().stream()
        .map(roleName -> roleRepository.findByRolename(roleName)
            .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
        .collect(Collectors.toSet());

    user.setRoles(roles);

    // Lưu user
    user = userRepository.save(user);

    String token = jwtService.generateToken(new CustomUserDetails(user));
    return new AuthenticationResponse(token);
}

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPasswordhash(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setEmployeeid(request.getEmployeeId());

        user = userRepository.save(user);

        // Gán roles
        for (String roleName : request.getRoles()) {
            Role role = roleRepository.findByRolename(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRoleRepository.save(userRole);
        }

        String token = jwtService.generateToken(new CustomUserDetails(user));
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordhash())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(new CustomUserDetails(user));
        return new AuthenticationResponse(token);
    }
}
