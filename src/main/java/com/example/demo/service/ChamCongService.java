package com.example.demo.service;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.repository.ChamCongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChamCongService {
	@Autowired
	private ChamCongRepository chamCongRepository;


	public ApiResponse getAllChamCong() {
		return new ApiResponse("failed", chamCongRepository.findAll());
	}
}
