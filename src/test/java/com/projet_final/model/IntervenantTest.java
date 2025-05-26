package com.projet_final.model;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

public class IntervenantTest {
    

    @Test
    void testConstructorAndGetters() {
        Intervenant org1 = new Intervenant("O1", "Janne", "janne@gmail.com", "IA");
        assertEquals("O1", org1.getId());
        assertEquals("Janne", org1.getNom());
        assertEquals("janne@gmail.com", org1.getEmail());
        
    }


    @Test
    void testSetters() {
        Intervenant org = new Intervenant("O1", "Charline", "charline@mail.com", "IA");
        org.setId("O4");
        org.setNom("Davy");
        org.setEmail("davy@mail.com");
        org.setSpecialite("Mathematique");
        assertEquals("O4", org.getId());
        assertEquals("Davy", org.getNom());
        assertEquals("davy@mail.com", org.getEmail());
        assertEquals("Mathematique", org.getSpecialite());
    }
}
