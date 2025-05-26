package com.projet_final.model;

import java.time.LocalDateTime;
import java.util.List;

public class Conference extends Evenement {
    private String theme;
    //definir la liste des intervenants proper a chaque conference
    private List<Intervenant> intervenants;
    
    //constructeurs par défaut
    public Conference() {
        super();
    }
    //constructeurs avec parametres
    public Conference(String id, String nom, LocalDateTime date, String lieu, int capaciteMax, String theme, List<Intervenant> intervenants) {
        super(id, nom, date, lieu, capaciteMax);
        this.theme = theme;
        this.intervenants = intervenants;
    }

    @Override
    public String afficherDetails() {
        return "Conférence: " + nom + " | Thème: " + theme + " | Lieu: " + lieu + " | Date: " + date;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<Intervenant> getIntervenants() {
        return intervenants;
    }

    public void setIntervenants(List<Intervenant> intervenants) {
        this.intervenants = intervenants;
    }
}