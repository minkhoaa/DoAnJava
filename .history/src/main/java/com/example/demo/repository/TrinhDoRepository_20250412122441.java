package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TrinhDo;

@Repository
public interface TrinhDoRepository extends JpaRepository<TrinhDo, Long> {
}