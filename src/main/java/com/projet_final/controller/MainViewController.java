package com.projet_final.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class MainViewController {
    @FXML private StackPane contentPane;

    @FXML
    public void showDashboard() throws Exception {
        setContent(loadView("/view/DashboardView.fxml"));
    }

    @FXML
    public void showEvents() throws Exception {
        setContent(loadView("/view/EventListView.fxml"));
    }

    @FXML
    public void showParticipants() throws Exception {
        setContent(loadView("/view/ParticipantListView.fxml"));
    }

    @FXML
    public void showOrganisateurs() throws Exception {
        setContent(loadView("/view/OrganisateurListView.fxml"));
    }

    @FXML
    public void showNotifications() throws Exception {
        setContent(loadView("/view/NotificationView.fxml"));
    }

    private void setContent(Node node) {
        contentPane.getChildren().setAll(node);
    }

    private Parent loadView(String fxmlPath) throws Exception {
        return FXMLLoader.load(getClass().getResource(fxmlPath));
    }
}