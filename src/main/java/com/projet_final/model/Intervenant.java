package com.projet_final.model;

public class Intervenant extends Participant {
    private String specialite;

    public Intervenant(String id, String nom, String email, String specialite) {
        super(id, nom, email);
        this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}