package com.projet_final;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

import com.projet_final.model.Concert;
import com.projet_final.model.Evenement;

/**
 * JavaFX App
 */
public class App {

    public static void main(String[] args) {
         Evenement e1 = new Concert("E1", "Concert Pop", LocalDateTime.of(2025, 6, 10, 21, 30), "Palais des Congres", 1000, "Adele", "pop" );
         System.out.println(e1.afficherDetails());
    }

}