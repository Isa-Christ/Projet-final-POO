package com.projet_final.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.projet_final.model.Evenement;
import com.projet_final.service.GestionEvenements;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;

public class DashboardController {
    
    @FXML private Label eventCountLabel;
    @FXML private Label participantCountLabel;
    @FXML private Label notificationCountLabel;
    @FXML private ListView<String> upcomingEventsList;
    @FXML private ListView<String> recentNotificationsList;
    
    private GestionEvenements gestionEvenements;
    private ObservableList<String> notifications;
    
    @FXML
    public void initialize() {
        gestionEvenements = GestionEvenements.getInstance();
        try {
            gestionEvenements.loadFromFile("evenements.json");
        } catch (IOException e) {
            // Fichier non trouvé - normal au premier lancement
        }
        notifications = FXCollections.observableArrayList();
        
        updateStats();
        updateUpcomingEvents();
        initializeNotifications();
    }
    
    private void updateStats() {
        // Compte les événements à venir
        long upcomingEvents = gestionEvenements.getEvenements().values().stream()
            .filter(e -> e.getDate().isAfter(LocalDateTime.now()))
            .count();
        eventCountLabel.setText(String.valueOf(upcomingEvents));
        
        // Compte total des participants
        long totalParticipants = gestionEvenements.getEvenements().values().stream()
            .mapToLong(e -> e.getParticipants().size())
            .sum();
        participantCountLabel.setText(String.valueOf(totalParticipants));
        
        // Notifications non lues (à implémenter selon ton système de notifications)
        notificationCountLabel.setText("0");
    }
    
    private void updateUpcomingEvents() {
        // Récupère les 5 prochains événements
        List<String> events = gestionEvenements.getEvenements().values().stream()
            .filter(e -> e.getDate().isAfter(LocalDateTime.now()))
            .sorted((e1, e2) -> e1.getDate().compareTo(e2.getDate()))
            .limit(5)
            .map(e -> e.getNom() + " - " + e.getDate())
            .collect(Collectors.toList());
            
        upcomingEventsList.setItems(FXCollections.observableArrayList(events));
    }
    
    private void initializeNotifications() {
        recentNotificationsList.setItems(notifications);
    }
    
    // Méthode appelée par l'Observable pour les notifications en temps réel
    public void addNotification(String message) {
        notifications.add(0, message); // Ajoute au début de la liste
        if (notifications.size() > 10) { // Garde les 10 plus récentes
            notifications.remove(10);
        }
        updateStats(); // Met à jour les compteurs
    }
    
    // Méthode pour rafraîchir manuellement le tableau de bord
    public void refresh() {
        updateStats();
        updateUpcomingEvents();
    }
}