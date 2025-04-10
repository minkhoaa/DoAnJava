package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;
import com.example.demo.filter.JwtAuthFilter;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repo;
    private final JwtAuthFilter jwtAuthFilter;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repo, JwtAuthFilter jwtAuthFilter) {
        this.repo = repo;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) new CustomUserDetails(repo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }
}
