package com.example.demo.repository;

import com.example.demo.dto.response.NghiViecDto;
import com.example.demo.entity.LoaiCong;
import com.example.demo.entity.NghiViec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface NghiViecRepository extends JpaRepository<NghiViec, Long>{
    List<NghiViec> findByNhanVien_Id(Long manv);
}
