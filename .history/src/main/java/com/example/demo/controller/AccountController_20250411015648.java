package com.example.demo.controller;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/account")
public class AccountController {
    private UserRepository repo;
    
    public AccountController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('User')")
    public List<User> getAll() {
       return repo.findAll();
    }

}
