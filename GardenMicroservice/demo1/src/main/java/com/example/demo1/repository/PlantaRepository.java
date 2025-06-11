package com.example.demo1.repository;

import com.example.demo1.domain.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantaRepository extends JpaRepository<Planta, Integer> {
    @Query("""
    SELECT p FROM Planta p
    WHERE (:tip IS NULL OR p.tip = :tip)
      AND (:specie IS NULL OR p.specie = :specie)
      AND (:planteCarnivore IS NULL OR p.planteCarnivore = :planteCarnivore)
""")
    List<Planta> filterPlante(@Param("tip") String tip,
                              @Param("specie") String specie,
                              @Param("planteCarnivore") Boolean planteCarnivore);

    List<Planta> findByDenumireOrSpecie(String denumire, String specie);

    @Query("SELECT p.tip, COUNT(p) FROM Planta p GROUP BY p.tip")
    List<Object[]> numarPlantePeTip();
    List<Planta> findByTip(String tip);
    List<Planta> findBySpecie(String specie);



}
