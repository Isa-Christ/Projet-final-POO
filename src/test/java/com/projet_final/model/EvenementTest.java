package com.projet_final.model;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.projet_final.exception.CapaciteMaxAtteinteException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EvenementTest {
    

    @Test
    public void testAjouterParticipant() throws CapaciteMaxAtteinteException{
        Evenement e1 = new Conference("e2", "Conference POO", LocalDateTime.of(2025, 6, 10, 20, 0), "test", 50, "test", null);
        Participant participant = new Participant("P1", "Nathan", "Nathan@gmail.com");
        e1.ajouterParticipant(participant);

        List<Participant> participants = e1.getParticipants();
        assertEquals(1, participants.size());
        assertEquals(participant, participants.get(0));
    }

    @Test
    void testConstructorAndGetters(){
        Evenement e1 = new Concert("E1", "Concert Pop", LocalDateTime.of(2025, 6, 10, 21, 30), "Palais des Congres", 1000, "Adele", "pop" );
        assertEquals("E1", e1.getId());
        assertEquals("Concert Pop", e1.getNom());
        assertEquals(LocalDateTime.of(2025, 6, 10, 21, 30), e1.getDate());
        assertEquals("Palais des Congres", e1.getLieu());
        assertEquals(1000, e1.getCapaciteMax());
    }

    @Test
    void testSetters(){
        Evenement e1 = new Concert("E1", "Concert Pop", LocalDateTime.of(2025, 6, 10, 21, 30), "Palais des Congres", 1000, "Adele", "pop" );

        e1.setCapaciteMax(5000);
        e1.setDate(LocalDateTime.of(2027,8,10,20,0));
        e1.setId("E2");
        e1.setLieu("Olympia");
        e1.setNom("Le concert de l'annee");

        assertEquals("E2", e1.getId());
        assertEquals("Le concert de l'annee", e1.getNom());
        assertEquals(LocalDateTime.of(2027,8,10,20,0), e1.getDate());
        assertEquals("Olympia", e1.getLieu());
        assertEquals(5000, e1.getCapaciteMax());
    }

    @Test
    void test_capaciteMaxAtteinte_throwsCapaciteMaxAtteinte() throws CapaciteMaxAtteinteException{
        Evenement e1 = new Concert("E1", "Concert Pop", LocalDateTime.of(2025, 6, 10, 21, 30), "Palais des Congres", 1, "Adele", "pop" );

        Participant participant1 = new Participant("P1", "Berkel", "Berkel@gmail.com");
        Participant participant2 = new Participant("P2", "Nathan", "Nathan@gmail.com");
        e1.ajouterParticipant(participant1);
        assertThrows(CapaciteMaxAtteinteException.class, ()->{
            e1.ajouterParticipant(participant2);
        });
    }
}
