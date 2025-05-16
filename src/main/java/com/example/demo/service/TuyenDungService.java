package com.example.demo.service;

import com.example.demo.dto.request.CandidateByJobIdDto;
import com.example.demo.dto.request.TokenRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.repository.TuyenDungRepository;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
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


}
