package com.example.demo2.domain;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "utilizator")
@Builder
public class Utilizator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUtilizator;
    @Column(nullable = false)
    private String nume;

    @Column(nullable = false)
    private String prenume;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String parola;

    @Column(nullable = false)
    private String tipUtilizator;

    public Utilizator() {
    }

    public Utilizator(Integer idUtilizator, String nume, String prenume, String username, String parola, String tipUtilizator) {
        this.idUtilizator = idUtilizator;
        this.nume = nume;
        this.prenume = prenume;
        this.username = username;
        this.parola = parola;
        this.tipUtilizator = tipUtilizator;
    }

    public Utilizator(String nume, String prenume, String username, String parola, String tipUtilizator) {
        this.nume = nume;
        this.prenume = prenume;
        this.username = username;
        this.parola = parola;
        this.tipUtilizator = tipUtilizator;
    }

    public Integer getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(Integer idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getTipUtilizator() {
        return tipUtilizator;
    }

    public void setTipUtilizator(String tipUtilizator) {
        this.tipUtilizator = tipUtilizator;
    }
}