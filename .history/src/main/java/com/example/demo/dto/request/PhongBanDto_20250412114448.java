package com.example.demo.dto.request;

import jakarta.persistence.Column;

public class PhongBanDto {

    private Long id;
    private String name;

    public Long getId() {
        return this.id;
    }
        
    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }
        
    public void setName(String name) {
        this.name = name; 
    }
}
