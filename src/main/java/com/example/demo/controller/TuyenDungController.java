package com.example.demo.controller;


import com.example.demo.dto.request.CandidateByJobIdDto;
import com.example.demo.dto.request.TokenRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.TuyenDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tuyendung")
public class TuyenDungController {

    @Autowired
    private TuyenDungService tuyenDungService;

    @PostMapping("/getAll-DangXetDuyet")
    public ResponseEntity<ApiResponse> getAllTuyenDungDangXetDuyet(TokenRequest tokenRequest) {
        return ResponseEntity.ok(tuyenDungService.getAllTuyenDungDangXetDuyet(tokenRequest));
    }
    @PostMapping("/getAllCandidatesByJobId")
    public ResponseEntity<ApiResponse> getAllCandidatesByJobId(CandidateByJobIdDto dto) {
        return  ResponseEntity.ok(tuyenDungService.getCandidatesByJobId(dto));

    }

}
