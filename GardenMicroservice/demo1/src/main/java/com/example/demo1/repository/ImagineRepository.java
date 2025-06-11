package com.example.demo1.repository;

import com.example.demo1.domain.Imagine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagineRepository extends JpaRepository<Imagine, Integer> {
}
