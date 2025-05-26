package com.projet_final.service;

import java.time.LocalDateTime;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.projet_final.exception.EvenementDejaExistantException;
import com.projet_final.model.Concert;
import com.projet_final.model.Conference;
import com.projet_final.model.Evenement;

public class GestionEvenementsTest {
    private GestionEvenements  gest;

    @BeforeEach
    void setUp(){
        gest = new GestionEvenements();
    }

    @Test
    void testAjouterEvenement() throws EvenementDejaExistantException{
        //ce test vérifie qu'après l'ajout d'un événement, la taille de la map augmente
        Evenement e2 = new Conference("e2", "Conference POO", LocalDateTime.of(2025, 6, 10, 20, 0), "test", 50, "test", null);
        gest.ajouterEvenement(e2);
        assertEquals(1, gest.getEvenements().size());
    }

    

    @Test
    void testSupprimerEvenement() throws EvenementDejaExistantException{
        //vérifie que lorsqu'un événement est supprimé, il n'appartient plus à la Map des événements
        Evenement e = new Concert("e1", "Concert Rock", LocalDateTime.of(2025, 7, 11,10, 0), "test", 50, "test","test");
        gest.ajouterEvenement(e);
        gest.supprimerEvenement(e);
        assertFalse(gest.getEvenements().containsKey(e.getId()));
        assertFalse(gest.getEvenements().containsValue(e));
    }

    @Test
    void testRechercherEvenement() throws EvenementDejaExistantException{
        Evenement e = new Concert("e1", "Concert Rock", LocalDateTime.of(2025, 7, 11,10, 0), "test", 50, "test","test");
        gest.ajouterEvenement(e);
        assertEquals(e, gest.rechercherEvenement(e.getId()));
    }

    @Test
    void testGetEvenements()throws EvenementDejaExistantException{
        Evenement e = new Concert("e1", "Concert Rock", LocalDateTime.of(2025, 7, 11,10, 0), "test", 50, "test","test");
        gest.ajouterEvenement(e);
        Map<String, Evenement> evs = gest.getEvenements();

        assertEquals(gest.getEvenements().size(), evs.size());
        assertTrue(evs.containsKey(e.getId()));
    }

    @Test
    void test_evenementExistant__throwsEvenementDejaExistantException() throws EvenementDejaExistantException{
        Evenement e = new Concert("e1", "Concert Rock", LocalDateTime.of(2025, 7, 11,10, 0), "test", 50, "test","test");
        gest.ajouterEvenement(e);
        assertThrows(EvenementDejaExistantException.class, ()->{
            gest.ajouterEvenement(e);
        });
    }
}
