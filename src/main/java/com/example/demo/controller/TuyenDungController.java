package com.example.demo.controller;


import com.cloudinary.Api;
import com.example.demo.dto.request.CandidateByJobIdDto;
import com.example.demo.dto.request.TokenRequest;
import com.example.demo.dto.request.TuyenDungInputDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.TuyenDungService;
import org.hibernate.boot.jaxb.cfg.spi.JaxbCfgMappingReferenceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tuyendung")
public class TuyenDungController {

    @Autowired
    private TuyenDungService tuyenDungService;

    @GetMapping("/getAll-DangXetDuyet")
    public ResponseEntity<ApiResponse> getAllTuyenDungDangXetDuyet( ) {
        return ResponseEntity.ok(tuyenDungService.getAllTuyenDungDangXetDuyet());
    }
    @PostMapping("/getAllCandidatesByJobId")
    public ResponseEntity<ApiResponse> getAllCandidatesByJobId(@RequestBody CandidateByJobIdDto dto) {
        return  ResponseEntity.ok(tuyenDungService.getCandidatesByJobId(dto));

    }

    @PostMapping("/addNewTuyenDung")
    public ResponseEntity<ApiResponse> postNewTuyenDung(@RequestBody TuyenDungInputDto dto) {
        return ResponseEntity.ok(tuyenDungService.addNewTuyenDung(dto));

    }
    @PostMapping("/accepttuyendung")
    public ResponseEntity<ApiResponse> acceptTuyendung(@RequestParam Long candidateId) {
        return ResponseEntity.ok(tuyenDungService.acceptCandidate(candidateId));
    }


}
