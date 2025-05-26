package com.projet_final.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

public class OrganisateurTest {
    
    @Test
    void testConstructorAndGetters() {
        Organisateur org1 = new Organisateur("O1", "Janne", "janne@gmail.com");
        assertEquals("O1", org1.getId());
        assertEquals("Janne", org1.getNom());
        assertEquals("janne@gmail.com", org1.getEmail());
        assertNotNull(org1.getEvenementsOrganises());
        assertTrue(org1.getEvenementsOrganises().isEmpty());
    }

    @Test
    void testAjouterEvenement() {
        Organisateur org = new Organisateur("O1", "Jean", "jean@mail.com");
        Concert concert = new Concert("E1", "Concert Test", LocalDateTime.of(2025, 7, 11, 20, 0), "Stade", 100, "Artiste", "Pop");
        org.ajouterEvenement(concert);

        List<Evenement> evenements = org.getEvenementsOrganises();
        assertEquals(1, evenements.size());
        assertEquals(concert, evenements.get(0));
    }

    @Test
    void testSetters() {
        Organisateur org = new Organisateur("O1", "Charline", "charline@mail.com");
        org.setId("O4");
        org.setNom("Davy");
        org.setEmail("davy@mail.com");

        assertEquals("O4", org.getId());
        assertEquals("Davy", org.getNom());
        assertEquals("davy@mail.com", org.getEmail());
    }
}
