package com.example.demo.service;

import com.example.demo.dto.request.CandidateByJobIdDto;
import com.example.demo.dto.request.TokenRequest;
import com.example.demo.dto.request.TuyenDungInputDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.TuyenDung;
import com.example.demo.repository.TuyenDungRepository;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TuyenDungService {
    @Autowired
    private TuyenDungRepository tuyenDungRepository;
    @Autowired
    private AuthService authService;
    private AuthenticationManager authenticationManager;

    public ApiResponse getAllTuyenDungDangXetDuyet(TokenRequest tokenRequest) {

        UserResponse user = authService.authenticate(tokenRequest);
        if (user == null) throw new ResponseStatusException(org.springframework.http.HttpStatus.UNAUTHORIZED, "Invalid token");

        return new ApiResponse("Success", tuyenDungRepository.findByTrangthai("Đang xét duyệt"));

    }
    public ApiResponse getCandidatesByJobId(CandidateByJobIdDto candidateByJobIdDto) {
        UserResponse user = authService.authenticate(new TokenRequest(candidateByJobIdDto.getToken() ));
        if (user == null) throw new ResponseStatusException(org.springframework.http.HttpStatus.UNAUTHORIZED, "Invalid token");
        return new ApiResponse("Success",tuyenDungRepository.findById(candidateByJobIdDto.getJobId()));
    }

    public ApiResponse addNewTuyenDung(TuyenDungInputDto tuyenDungInputDto) {
        UserResponse user = authService.authenticate(new TokenRequest(tuyenDungInputDto.getToken()) );
        if (user == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");

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


}
