package com.projet_final.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.projet_final.model.Evenement;

import java.util.Map;
import java.io.File;
import java.io.IOException;


public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
    }

    //sauvegarder la liste des evenements
    public static void saveEvenements(Map<String, Evenement> evenements, String filepath) throws IOException{
        mapper.writerFor(new TypeReference<Map<String, Evenement>>(){}).withDefaultPrettyPrinter()
            .writeValue(new File(filepath), evenements);
    }

    //charger la liste des evenements
    public static Map<String, Evenement> loadEvenements(String filepath) throws IOException{
        return mapper.readValue(new File(filepath),
            new TypeReference<Map<String, Evenement>>() {});
    }
}