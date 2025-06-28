package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.response.NhanVienDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.NhanVienService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/nhanvien")
public class NhanVienController {

    private final RoleRepository roleRepository;
    @Autowired
    private NhanVienService nhanVienService;


    NhanVienController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllNhanVien() {
        return ResponseEntity.ok(nhanVienService.getAllNhanVien());
    }
    
    @GetMapping("/getByID/{id}")
   // @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> getNhanVienByID(@PathVariable Long id) {
        return ResponseEntity.ok(nhanVienService.getNhanVienByID(id));
    }

    @PostMapping("/addnhanvien")
   // @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<ApiResponse> addNhanVien(@RequestBody NhanVienDto nhanVienDto) {
        return ResponseEntity.ok(nhanVienService.addNhanVien(nhanVienDto));
    }

    @PutMapping("/updatenhanvien/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> updateNhanVien(@PathVariable Long id, @RequestBody NhanVienDto nhanVienDto) {
        return ResponseEntity.ok(nhanVienService.updatenhanVien(id, nhanVienDto));
    }
    @DeleteMapping("/deleteNhanvien/{id}")    
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteNhanVien(@PathVariable Long id) {
        return ResponseEntity.ok(nhanVienService.deleteNhanVien(id));
    }
}