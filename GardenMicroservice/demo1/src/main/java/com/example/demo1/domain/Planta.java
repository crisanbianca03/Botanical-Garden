package com.example.demo1.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Planta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlanta;

    private String denumire;
    private String tip;
    private String specie;

    @Column(name = "plante_carnivore")
    private Boolean planteCarnivore;

    private String descriere;

    @OneToMany(mappedBy = "planta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exemplar> exemplare;

    public Integer getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(Integer idPlanta) {
        this.idPlanta = idPlanta;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public Boolean getPlanteCarnivore() {
        return planteCarnivore;
    }

    public void setPlanteCarnivore(Boolean planteCarnivore) {
        this.planteCarnivore = planteCarnivore;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public List<Exemplar> getExemplare() {
        return exemplare;
    }

    public void setExemplare(List<Exemplar> exemplare) {
        this.exemplare = exemplare;
    }

    @Override
    public String toString() {
        return "Planta{" +
                "idPlanta=" + idPlanta +
                ", denumire='" + denumire + '\'' +
                ", tip='" + tip + '\'' +
                ", specie='" + specie + '\'' +
                ", planteCarnivore=" + planteCarnivore +
                ", descriere='" + descriere + '\'' +
                ", exemplare=" + exemplare +
                '}';
    }
}
