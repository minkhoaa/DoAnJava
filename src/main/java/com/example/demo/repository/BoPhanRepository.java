package com.example.demo.repository;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BoPhan;

public interface BoPhanRepository extends JpaRepository<BoPhan, Long> {
    

}
