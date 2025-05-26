package com.projet_final.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ConferenceTest {
    @Test
    void testAfficherDetails(){
        Evenement e1 = new Conference("e2", "Conference POO", LocalDateTime.of(2025, 6, 10, 20, 0), "Olympia", 50, "Introduction a la POO", new ArrayList<Intervenant>());
        String message_attendu = "Conférence: Conference POO | Thème: Introduction a la POO | Lieu: Olympia | Date: 2025-06-10T20:00";
        String message = e1.afficherDetails();

        assertEquals(message_attendu, message);
    }
}
