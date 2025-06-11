package com.example.demo1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Imagine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImagine;

    private String denumire;

    @ManyToOne
    @JoinColumn(name = "idExemplar")
    @JsonIgnore
    private Exemplar exemplar;

    @Override
    public String toString() {
        return "Imagine{" +
                "idImagine=" + idImagine +
                ", denumire='" + denumire + '\'' +
                ", exemplar=" + exemplar +
                '}';
    }

    public Integer getIdImagine() {
        return idImagine;
    }

    public void setIdImagine(Integer idImagine) {
        this.idImagine = idImagine;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }
}
