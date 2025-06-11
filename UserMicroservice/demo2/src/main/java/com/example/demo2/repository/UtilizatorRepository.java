package com.example.demo2.repository;

import com.example.demo2.domain.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilizatorRepository extends JpaRepository<Utilizator, Integer> {
    Optional<Utilizator> findByUsername(String username);
}
