package com.example.demo1.domain.dto;

import lombok.Builder;


public class PlantaDTO {
    public int id;
    public String denumire;
    public String tip;
    public String specie;

    public Boolean planteCarnivore;
    public String descriere;

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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
