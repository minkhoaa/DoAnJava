package com.example.demo.repository;

import com.example.demo.entity.TuyenDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TuyenDungRepository extends JpaRepository<TuyenDung, Long> {
    List<TuyenDung> findByTrangthai(String trangthai);

    List<TuyenDung> findByVitri(String vitri);
}
