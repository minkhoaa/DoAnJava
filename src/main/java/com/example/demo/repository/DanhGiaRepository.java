package com.example.demo.repository;

import com.example.demo.dto.response.DanhGiaDto;
import com.example.demo.entity.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia, Long> {
    List<DanhGia> findByNhanVien_Id(Long employeeId);

    List<DanhGia> findByNhanVien_IdAndKy(Long nhanVienId, Integer ky);
    List<DanhGia> findByNhanVien_IdAndNam(Long nhanVienId, Integer nam);

    List<DanhGia> findByNhanVien_IdAndKyAndNam(Long nhanVienId, Integer ky, Integer nam);
    List<DanhGia> findByKy(Integer ky);
    List<DanhGia> findByNam(Integer nam);
    List<DanhGia> findByKyAndNam(Integer ky, Integer nam);


}
