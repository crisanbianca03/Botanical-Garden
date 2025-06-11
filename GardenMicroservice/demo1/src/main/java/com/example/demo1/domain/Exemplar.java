package com.example.demo1.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Exemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExemplar;

    private String zonaGradina;
    private String denumire;

    @Column(name = "data_plantarii")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPlantarii;

    @Column(name = "caracteristici_specifice")
    private String caracteristiciSpecifice;

    @ManyToOne
    @JoinColumn(name = "idPlanta")
    @JsonIgnore
    private Planta planta;

    @OneToMany(mappedBy = "exemplar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagine> imagini;

    public Integer getIdExemplar() {
        return idExemplar;
    }

    public void setIdExemplar(Integer idExemplar) {
        this.idExemplar = idExemplar;
    }

    public String getZonaGradina() {
        return zonaGradina;
    }

    public void setZonaGradina(String zonaGradina) {
        this.zonaGradina = zonaGradina;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public LocalDate getDataPlantarii() {
        return dataPlantarii;
    }

    public void setDataPlantarii(LocalDate dataPlantarii) {
        this.dataPlantarii = dataPlantarii;
    }

    public String getCaracteristiciSpecifice() {
        return caracteristiciSpecifice;
    }

    public void setCaracteristiciSpecifice(String caracteristiciSpecifice) {
        this.caracteristiciSpecifice = caracteristiciSpecifice;
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    public List<Imagine> getImagini() {
        return imagini;
    }

    public void setImagini(List<Imagine> imagini) {
        this.imagini = imagini;
    }
}
