package com.example.demo.repository;

import com.example.demo.entity.Luong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LuongRepository extends JpaRepository<Luong, Long> {
    List<Luong> findByNhanVien_Id(Long employeeId);
    List<Luong> findByThang(Integer thang);
    List<Luong> findByNam(Integer nam);
    List<Luong> findByNhanVien_IdAndThang(Long employeeId, Integer thang);
    List<Luong> findByNhanVien_IdAndThangAndNam(Long employeeId, Integer thang, Integer nam);
    List<Luong> findByNhanVien_IdAndNam(Long employeeId, Integer nam);
    List<Luong> findByNamAndThang(Integer nam, Integer thang);

}
