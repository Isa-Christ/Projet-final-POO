package com.projet_final.model;

import java.time.LocalDateTime;

public class Concert extends Evenement {
    private String artiste;
    private String genreMusical;

    public Concert(){
        super();
    }

    public Concert(String id, String nom, LocalDateTime date, String lieu, int capaciteMax, String artiste, String genreMusical) {
        super(id, nom, date, lieu, capaciteMax);
        this.artiste = artiste;
        this.genreMusical = genreMusical;
    }

    @Override
    public String afficherDetails() {
        return "Concert: " + nom + " | Artiste: " + artiste + " | Genre: " + genreMusical + " | Lieu: " + lieu + " | Date: " + date;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getGenreMusical() {
        return genreMusical;
    }

    public void setGenreMusical(String genreMusical) {
        this.genreMusical = genreMusical;
    }
}