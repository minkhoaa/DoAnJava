package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"bophan\"") 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoPhan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"idbp\"")
    private Long id;

    @Column(name = "\"tenpb\"")
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
