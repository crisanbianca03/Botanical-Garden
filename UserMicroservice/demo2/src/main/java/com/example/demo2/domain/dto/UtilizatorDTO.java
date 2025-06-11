package com.example.demo2.domain.dto;

import lombok.Builder;

@Builder
public class UtilizatorDTO {

    private Integer idUtilizator;
    private String username;
    private String tipUtilizator;
    private String nume;
    private String prenume;
    private String parola;

    public UtilizatorDTO(Integer idUtilizator, String username, String tipUtilizator, String nume, String prenume, String parola) {
        this.idUtilizator = idUtilizator;
        this.username = username;
        this.tipUtilizator = tipUtilizator;
        this.nume = nume;
        this.prenume = prenume;
        this.parola = parola;
    }

    public Integer getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(Integer idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTipUtilizator() {
        return tipUtilizator;
    }

    public void setTipUtilizator(String tipUtilizator) {
        this.tipUtilizator = tipUtilizator;
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

    public String getParola() {return parola;}

    public void setParola(String parola) {this.parola = parola;}

    @Override
    public String toString() {
        return "username='" + username + '\'' +
                ", tipUtilizator='" + tipUtilizator + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", parola='" + parola + '\'';
    }
}