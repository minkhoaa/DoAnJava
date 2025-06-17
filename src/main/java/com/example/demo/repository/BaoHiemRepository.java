package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BaoHiem;

@Repository
public interface BaoHiemRepository extends JpaRepository<BaoHiem, Long> {
    BaoHiem findByNhanVien_Id(Long employeeId);
}