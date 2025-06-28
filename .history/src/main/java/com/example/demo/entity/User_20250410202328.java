package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "\"users\"") // Ánh xạ bảng "users"
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"userid\"") // Ánh xạ cột "userid"
    private Long userid;

    @Column(name = "\"username\"", unique = true) // Ánh xạ cột "username"
    private String username;

    @Column(name = "\"passwordhash\"", nullable = false) // Ánh xạ cột "passwordhash"
    private String passwordhash;

    @Column(name = "\"email\"", unique = true) // Ánh xạ cột "email"
    private String email;

    @ManyToOne
    @JoinColumn(name = "\"employeeid\"") // Ánh xạ cột "employeeid"
    private NhanVien employee;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "\"userroles\"", // Ánh xạ bảng trung gian "userroles"
        joinColumns = @JoinColumn(name = "\"userid\""), // Ánh xạ cột "userid" trong bảng trung gian
        inverseJoinColumns = @JoinColumn(name = "\"roleid\"") // Ánh xạ cột "roleid" trong bảng trung gian
    )
    private Set<Role> roles = new HashSet<>();

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
