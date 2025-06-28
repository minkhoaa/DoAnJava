package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "nghiviec")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NghiViec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "manv")
    private NhanVien nhanVien;

    @Column(name = "tungay")
    private LocalDate tungay;

    @Column(name = "denngay")
    private LocalDate denngay;

    @Column(name = "lydo")
    private String lyDo;

    @Column(name = "quyetdinh")
    private String quyetDinh;
}
