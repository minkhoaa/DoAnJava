package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ChamCong;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChamCongRepository extends JpaRepository<ChamCong, Long> {


    List<ChamCong> findByNhanVienIdAndMonth(Long employeeId, Integer month);

    List<ChamCong> findByNhanVienIdAndNgay(Long employeeId, LocalDate date);
}