package com.example.demo2.domain.dto;

import lombok.Builder;

@Builder
public class ContactDTO {
    private Integer idContact;
    private String tip;
    private String informatie;
    private Integer idUtilizator;

    public ContactDTO(Integer idContact, String tip, String informatie, Integer idUtilizator) {
        this.idContact = idContact;
        this.tip = tip;
        this.informatie = informatie;
        this.idUtilizator = idUtilizator;
    }

    public Integer getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(Integer idUtilizator) {
        this.idUtilizator = idUtilizator;
    }


    public Integer getIdContact() {
        return idContact;
    }

    public void setIdContact(Integer idContact) {
        this.idContact = idContact;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getInformatie() {
        return informatie;
    }

    public void setInformatie(String informatie) {
        this.informatie = informatie;
    }

    @Override
    public String toString() {
        return "tip='" + tip + '\'' +
                ", informatie='" + informatie + '\'';
    }
}