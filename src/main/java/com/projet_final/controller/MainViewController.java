package com.projet_final.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import java.net.URL;

public class MainViewController {
    // Injection des composants FXML
    @FXML private StackPane contentPane;
    @FXML private Button dashboardBtn;
    @FXML private Button eventsBtn;
    @FXML private Button participantsBtn;
    @FXML private Button organisateursBtn;
    @FXML private Button notificationsBtn;

    /**
     * Initialise le contrôleur et charge la vue par défaut
     */
    @FXML
    public void initialize() {
        try {
            // Charge le dashboard par défaut au démarrage
            showDashboard(null);
        } catch (Exception e) {
            showError("Erreur lors du chargement du tableau de bord", e);
        }
    }

    /**
     * Affiche le tableau de bord
     */
    @FXML
    public void showDashboard(ActionEvent event) {
        loadAndSetContent("DashboardView");
    }

    /**
     * Affiche la liste des événements
     */
    @FXML
    public void showEvents(ActionEvent event) {
        loadAndSetContent("EventListView");
    }

    /**
     * Affiche la liste des participants
     */
    @FXML
    public void showParticipants(ActionEvent event) {
        loadAndSetContent("ParticipantListView");
    }

    /**
     * Affiche la liste des organisateurs
     */
    @FXML
    public void showOrganisateurs(ActionEvent event) {
        loadAndSetContent("OrganisateurListView");
    }

    /**
     * Affiche les notifications
     */
    @FXML
    public void showNotifications(ActionEvent event) {
        loadAndSetContent("NotificationView");
    }

    /**
     * Charge et affiche une vue FXML
     * @param viewName le nom de la vue sans extension ni chemin
     */
    private void loadAndSetContent(String viewName) {
        try {
            // Construit le chemin correct du fichier FXML
            String fxmlPath = "/view/" + viewName + ".fxml";
            
            // Vérifie si le fichier existe
            URL resource = getClass().getResource(fxmlPath);
            if (resource == null) {
                throw new IOException("Fichier FXML non trouvé: " + fxmlPath);
            }

            // Charge la vue
            FXMLLoader loader = new FXMLLoader(resource);
            Parent view = loader.load();

            // Vérifie si le chargement a réussi
            if (view == null) {
                throw new IOException("Échec du chargement de la vue: " + viewName);
            }

            // Met à jour le contenu
            setContent(view);
        } catch (Exception e) {
            e.printStackTrace(); // Pour voir l'erreur complète
            showError("Erreur lors du chargement de la vue: " + viewName, e);
        }
    }

    /**
     * Met à jour le contenu de la zone principale
     * @param node le nouveau contenu à afficher
     */
    private void setContent(Node node) {
        if (node != null && contentPane != null) {
            // Efface l'ancien contenu et ajoute le nouveau
            contentPane.getChildren().clear();
            contentPane.getChildren().add(node);
        } else {
            showError("Erreur d'affichage", 
                     new IllegalStateException("Node ou ContentPane est null"));
        }
    }

    /**
     * Affiche une boîte de dialogue d'erreur
     * @param header le titre de l'erreur
     * @param e l'exception à afficher
     */
    private void showError(String header, Exception e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(header);
        alert.setContentText(e.getMessage());
        
        // Log l'erreur pour le débogage
        System.err.println(header);
        e.printStackTrace();
        
        alert.showAndWait();
    }
}