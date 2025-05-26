package com.projet_final.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes; 
import com.projet_final.exception.CapaciteMaxAtteinteException;
import com.projet_final.util.EvenementObservable;


//annotations pour specifier au fichier Json les classes filles
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)

@JsonSubTypes({
    @JsonSubTypes.Type(value = Concert.class, name = "concert"),
    @JsonSubTypes.Type(value = Conference.class, name = "conference")
})

public abstract class Evenement extends EvenementObservable{
    protected String id;
    protected String nom;
    protected LocalDateTime date;
    protected String lieu;
    protected int capaciteMax;
    protected List<Participant> participants = new ArrayList<>();

    public Evenement(){}

    //constructeur avec parametres
    public Evenement(String id, String nom, LocalDateTime date, String lieu, int capaciteMax) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.capaciteMax = capaciteMax;
    }

    // Getters et setters

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

    public LocalDateTime getDate() {
        
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    
    public boolean ajouterParticipant(Participant participant) throws CapaciteMaxAtteinteException {
        
        if (participants.size() < capaciteMax) {
            //ajouter le participant à la liste des participants à l'événement.
            participants.add(participant);

            //ajouter le participant à la liste des observateurs pour qu'il puisse recevoir les notifications.
            this.addObserver(participant);
            return true;

        } else {
            throw new CapaciteMaxAtteinteException("La capacité maximale pour cet événement est atteinte");
        }
    }

    public void annuler() {
        //envoyer une notification d'annulation de l'événement.
        this.notifyObserver(" l'événement "+getNom()+" a été annulé.");

        //vider la liste des participants.
        participants.clear();
    }


    public abstract String afficherDetails();


    public List<Participant> getParticipants() {
        return participants;
    }

}