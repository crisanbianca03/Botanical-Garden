package com.example.demo1.repository;

import com.example.demo1.domain.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Integer> {
    List<Exemplar> findByZonaGradinaIgnoreCase(String zonaGradina);
    List<Exemplar> findByDenumireContainingIgnoreCase(String denumire);
    @Query("SELECT e.planta.specie, COUNT(e) FROM Exemplar e GROUP BY e.planta.specie")
    List<Object[]> numarExemplarePeSpecie();

    @Query("SELECT e.zonaGradina, COUNT(e) FROM Exemplar e GROUP BY e.zonaGradina")
    List<Object[]> numarExemplarePeZona();

    List<Exemplar> findByPlanta_SpecieIgnoreCase(String specie);

}
