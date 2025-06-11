package com.example.demo1.domain.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;


public class ExemplarDTO {
    private int idPlanta;
    public String zonaGradina;
    public String denumire;
    public LocalDate dataPlantarii;
    public String caracteristiciSpecifice;
    private int idExemplar;


    public int getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(int idPlanta) {
        this.idPlanta = idPlanta;
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

    public int getIdExemplar() {
        return idExemplar;
    }

    public void setIdExemplar(int idExemplar) {
        this.idExemplar = idExemplar;
    }
}
