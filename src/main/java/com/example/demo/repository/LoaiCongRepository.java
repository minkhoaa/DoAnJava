package com.example.demo.repository;

import com.example.demo.entity.LoaiCong;
import org.springframework.beans.factory.aot.AotServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoaiCongRepository extends JpaRepository<LoaiCong, Long> {
    LoaiCong findByMachamcong(Long machamcong);
}
