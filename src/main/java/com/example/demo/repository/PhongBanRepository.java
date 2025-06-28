package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PhongBan;

@Repository
public interface PhongBanRepository  extends JpaRepository<PhongBan, Long>{

}
