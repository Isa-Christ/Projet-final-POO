package com.projet_final.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ConcertTest {
    
    @Test
    void testAfficherDetails(){
        Evenement e1 = new Concert("E1", "Concert Pop", LocalDateTime.of(2025, 6, 10, 21, 30), "Palais des Congres", 1000, "Adele", "pop" );
        String message_attendu = "Concert: Concert Pop | Artiste: Adele | Genre: pop | Lieu: Palais des Congres | Date: 2025-06-10T21:30";
        String message = e1.afficherDetails();

        assertEquals(message_attendu, message);
    }
}
