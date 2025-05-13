package com.example.demo.repository;

import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.ChamCong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChamCongRepository extends JpaRepository<ChamCong, Object>{
    List<ChamCong> findByNgayAndEmployeeId(LocalDate ngay, Long employeeId);

    List<ChamCong> findByNgay(LocalDate ngay);

    List<ChamCong> findByEmployeeId(Long employeeId);


}
