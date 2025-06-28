package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "\"phongban\"") 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PhongBan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"idpb\"")
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
