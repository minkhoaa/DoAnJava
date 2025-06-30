package com.example.demo.controller;


import com.example.demo.dto.request.AddNghiViecDto;
import com.example.demo.dto.response.DeclineNghiviec;
import com.example.demo.dto.response.NghiViecDto;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.repository.NghiViecRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.NghiViecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/nghiviec")
public class NghiViecController {
    @Autowired
    private NghiViecService nghiViecService;

    @Autowired
    private AuthService  authService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getAllByUser")
    public List<NghiViecDto> getAllByUser(Long userId) {
        return nghiViecService.GetAllByEmplopyeeId(userId);
    }
    @PostMapping("/addnghiviec")
    public NghiViecDto AddNghiViec(@RequestBody AddNghiViecDto nghiViecDto) {
        try {
            UserResponse employee = (UserResponse) authService.getCurrentUserInfo().getData();
            if (employee == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
            }
            return nghiViecService.AddNewNghiViec(nghiViecDto ,employee.getEmployeeId());
        }
        catch (ResponseStatusException e) {
            throw e;
        }
    };

    @GetMapping("/getall")
    public List<NghiViecDto> getAll() {
            return nghiViecService.GetAll();
    }

    @PostMapping("/approve")
    public NghiViecDto approveNghiViec(Long nghiViecId) {
        return nghiViecService.approveNghiViec(nghiViecId);
    }

    @PostMapping("/decline")
    public DeclineNghiviec  declineNghiviec(Long nghiViecId, String lido) {
        return  nghiViecService.declineNghiViec(nghiViecId,lido);
    }
}
