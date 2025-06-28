package com.example.demo.dto.request;

import jakarta.persistence.Column;

public class PhongBanDto {

  
    private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name; 
    }
}
