package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"danhgia\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DanhGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"id\"")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"employeeid\"", referencedColumnName = "\"manv\"")
    private NhanVien nhanVien;


    @Column(name = "\"nam\"")
    private Integer nam;

    @Column(name = "\"ky\"")
    private Integer ky;
    @Column(name = "\"diemso\"")
    private Integer diemSo;
    @Column(name = "\"nhanxet\"")
    private String nhanXet;


}
