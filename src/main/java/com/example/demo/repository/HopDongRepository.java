package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.HopDong;

@Repository
public interface HopDongRepository extends JpaRepository<HopDong, Long> {
}