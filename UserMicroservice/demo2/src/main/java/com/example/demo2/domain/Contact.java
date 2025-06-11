package com.example.demo2.domain;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "contact")
@Builder
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContact;

    @Column(nullable = false)
    private String tip;

    @Column(nullable = false)
    private String informatie;

    @ManyToOne
    @JoinColumn(name = "idUtilizator")
    private Utilizator utilizator;

    public Contact(Integer idContact, String tip, String informatie, Utilizator utilizator) {
        this.idContact = idContact;
        this.tip = tip;
        this.informatie = informatie;
        this.utilizator = utilizator;
    }

    public Contact() {

    }

    public Contact(String tip, String informatie, Utilizator utilizator) {
        this.tip = tip;
        this.informatie = informatie;
        this.utilizator = utilizator;
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

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }
}
