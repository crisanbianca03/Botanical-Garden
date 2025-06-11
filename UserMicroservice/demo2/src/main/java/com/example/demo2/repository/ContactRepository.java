package com.example.demo2.repository;

import com.example.demo2.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findByUtilizatorIdUtilizator(Integer idUtilizator);
}
