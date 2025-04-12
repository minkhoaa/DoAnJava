package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "\"chucvu\"") // Đảm bảo tên bảng khớp với cơ sở dữ liệu
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChucVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"idcv\"") // Tên cột trong cơ sở dữ liệu
    private Long id;

    @Column(name = "\"tencv\"") // Tên cột trong cơ sở dữ liệu
    private String name;

    // Getters và Setters (nếu không dùng Lombok)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}