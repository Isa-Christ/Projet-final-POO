package com.projet_final.model;

import com.projet_final.util.ParticipantObserver;

public class Participant implements ParticipantObserver{
    protected String id;
    protected String nom;
    protected String email;

    public Participant(String id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    //Getters et Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //implementation de la methode pour recevoir une notification
    public void update(String message){
        System.out.println("Cher participant "+ getNom()+" nous vous informons que :" +message); 
    }
}