package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tuyendung")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TuyenDung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hoten", length = 10)
    private String hoten;

    @Column(name = "ngaysinh")
    private LocalDate ngaysinh;

    @Column(name = "dienthoai", length = 15)
    private String dienthoai;

    @Column(name = "email", length = 10)
    private String email;

    @Column(name = "vitri", length = 100)
    private String vitri;

    @Column(name = "trangthai", length = 50)
    private String trangthai;


}
