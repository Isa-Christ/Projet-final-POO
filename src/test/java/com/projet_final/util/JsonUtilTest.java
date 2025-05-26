package com.projet_final.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.projet_final.model.Conference;
import com.projet_final.model.Concert;
import com.projet_final.model.Evenement;

public class JsonUtilTest {
    private static final String tempFile = "test_evenements.json";

    @AfterEach
    void cleanUp(){
        File file = new File(tempFile);
        //supprimer le fichier s'il existe
        if(file.exists()){
            file.delete();
        }
    }
        

    @Test
    void testSaveEvenements_createValidJsonFile() throws IOException{
        Concert e1 = new Concert("e1", "Concert Rock", LocalDateTime.of(2025, 7, 11,10, 0), "test", 50, "test","test");

        Conference e2 = new Conference("e2", "Conference POO", LocalDateTime.of(2025, 6, 10, 20, 0), "test", 50, "test", null);
        
        Map<String, Evenement> evenements = new HashMap<>();
        evenements.put(e1.getId(), e1);
        evenements.put(e2.getId(), e2);

        //le fichier  est automatiquement cree
        JsonUtil.saveEvenements(evenements, tempFile);
        
        File file = new File(tempFile);
        //verifier qu'il existe et qu'il n'est pas vide
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    @Test
    void testSaveEvenements_readsCorrectlyFromJson()throws IOException{
        Concert e1 = new Concert("e1", "Concert Rock", LocalDateTime.of(2025, 7, 11,10, 0), "test", 50, "test","test");

        Conference e2 = new Conference("e2", "Conference POO", LocalDateTime.of(2025, 6, 10, 20, 0), "test", 50, "test", null);
        
        Map<String, Evenement> evenements = new HashMap<>();
        evenements.put(e1.getId(), e1);
        evenements.put(e2.getId(), e2);

        //le fichier  est automatiquement cree
        JsonUtil.saveEvenements(evenements, tempFile);

        Map<String, Evenement> evenements_loaded = JsonUtil.loadEvenements(tempFile);

        assertEquals(evenements.size(), evenements_loaded.size());

        for(String key: evenements.keySet()){
            assertTrue(evenements_loaded.containsKey(key));

            assertEquals(evenements.get(key).getNom(), evenements_loaded.get(key).getNom());

            assertEquals(evenements.get(key).getDate(), evenements_loaded.get(key).getDate());
        }

    }

    @Test
    void testLoadUsers_fileNotFound_throwsIOException(){
        assertThrows(IOException.class, ()->{
            JsonUtil.loadEvenements("non_existent.json");   
        });
    }

    @Test
    void test_withEmptyMap_createEmptyHashMap() throws IOException{
        JsonUtil.saveEvenements(new HashMap<>(), tempFile);

        Map<String, Evenement> evenements = JsonUtil.loadEvenements(tempFile);
        assertTrue(evenements.isEmpty());
    }
}
