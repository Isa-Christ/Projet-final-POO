package com.projet_final.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.projet_final.exception.EvenementDejaExistantException;
import com.projet_final.model.Evenement;
import com.projet_final.util.JsonUtil;

public class GestionEvenements {
    private static GestionEvenements instance;
    private Map<String, Evenement> evenements = new HashMap<>();

    public GestionEvenements() {}

    public static GestionEvenements getInstance() {
        if (instance == null) {
            instance = new GestionEvenements();
        }
        return instance;
    }

    public void ajouterEvenement(Evenement evenement) throws EvenementDejaExistantException {
        //verfier si l'événement est déjà dans la liste sinon l'ajoute à la liste.
        if (evenements.containsKey(evenement.getId())) {
            //genere l'exception qui specifie que l'événement existe déjà.
            throw new EvenementDejaExistantException("L'événement que vous essayez d'ajouter existe déjà.");
        } else {
            evenements.put(evenement.getId(), evenement);
        }
    }

    public void supprimerEvenement(Evenement e) {
        //enleve l'evenement de la liste 
        evenements.remove(e.getId());
    }

    public Evenement rechercherEvenement(String id) {
        return evenements.get(id);
    }

    public Map<String, Evenement> getEvenements() {
        return evenements;
    }

    public void saveToFile(String filepath) throws IOException{
        JsonUtil.saveEvenements(evenements, filepath);
    }

    public void loadFromFile(String filepath) throws IOException{
        evenements = new HashMap<>(JsonUtil.loadEvenements(filepath));
    }
}