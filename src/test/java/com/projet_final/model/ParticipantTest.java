package com.projet_final.model;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ParticipantTest {

    @Test
    public void testConstructorAndGetters() {
        Participant participant = new Participant("1", "Alice", "alice@example.com");
        assertEquals("1", participant.getId());
        assertEquals("Alice", participant.getNom());
        assertEquals("alice@example.com", participant.getEmail());
    }

    @Test
    public void testSetters() {
        Participant participant = new Participant("1", "Alice", "alice@example.com");
        participant.setId("2");
        participant.setNom("Bob");
        participant.setEmail("bob@example.com");

        assertEquals("2", participant.getId());
        assertEquals("Bob", participant.getNom());
        assertEquals("bob@example.com", participant.getEmail());
    }

    @Test
    public void testUpdatePrintsCorrectMessage() {
        Participant participant = new Participant("1", "Alice", "alice@example.com");
        String message = "L'événement commence à 10h.";
        String expectedOutput = "Cher participant Alice nous vous informons que :" + message;

        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        participant.update(message);

        System.setOut(System.out); // Reset to standard output

        String actualOutput = outContent.toString().trim();
        assertTrue(actualOutput.contains(expectedOutput));
    }
}
