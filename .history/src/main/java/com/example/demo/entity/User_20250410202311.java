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
@Table(name = "\"users\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"userid\"")
    private Long userid;

    @Column(name = "\"username\"", unique = true)
    private String username;

    @Column(name = "\"passwordhash\"", nullable = false)
    private String passwordhash;

    @Column(name = "\"email\"", unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "\"employeeid\"")
    private NhanVien employee;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "\"userroles\"",
        joinColumns = @JoinColumn(name = "\"userid\""),
        inverseJoinColumns = @JoinColumn(name = "\"roleid\"")
    )
    private Set<Role> roles = new HashSet<>();
}

    
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

   

}
