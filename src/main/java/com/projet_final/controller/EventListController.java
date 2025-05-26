package com.projet_final.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.ListChangeListener;
import com.projet_final.model.Evenement;
import com.projet_final.service.GestionEvenements;
import java.time.LocalDateTime;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class EventListController {
    
    @FXML private TextField searchField;
    @FXML private TableView<Evenement> eventTable;
    @FXML private TableColumn<Evenement, String> colNom;
    @FXML private TableColumn<Evenement, LocalDateTime> colDate;
    @FXML private TableColumn<Evenement, String> colLieu;
    @FXML private TableColumn<Evenement, String> colType;
    @FXML private TableColumn<Evenement, Integer> colCapacite;
    @FXML private TableColumn<Evenement, Void> colActions;
    @FXML private ComboBox<String> typeFilter;
    @FXML private DatePicker dateFilter;
    @FXML private Pagination pagination;

    private GestionEvenements gestionEvenements;
    private FilteredList<Evenement> filteredEvents;
    private static final int ITEMS_PER_PAGE = 10;

    @FXML
    public void initialize() {
        gestionEvenements = GestionEvenements.getInstance();
        setupTableColumns();
        setupFilters();
        loadEvents();
        setupPagination();
        
        // Sauvegarde automatique après chaque modification
        eventTable.getItems().addListener((ListChangeListener<Evenement>) c -> {
            try {
                gestionEvenements.saveToFile("evenements.json");
            } catch (IOException e) {
                showError("Erreur de sauvegarde", e);
            }
        });
    }

    private void setupTableColumns() {
        colNom.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getNom()));
            
        colDate.setCellValueFactory(cellData -> 
            new SimpleObjectProperty<>(cellData.getValue().getDate()));
            
        colLieu.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getLieu()));
            
        colType.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));
            
        colCapacite.setCellValueFactory(cellData -> 
            new SimpleIntegerProperty(cellData.getValue().getCapaciteMax()).asObject());
        
        setupActionColumn();
    }

    private void setupActionColumn() {
        colActions.setCellFactory(col -> new TableCell<>() {
            private final Button editButton = new Button("Modifier");
            private final Button deleteButton = new Button("Supprimer");
            private final HBox actionBox = new HBox(5, editButton, deleteButton);

            {
                editButton.getStyleClass().addAll("action-button", "edit-button");
                deleteButton.getStyleClass().addAll("action-button", "delete-button");
                
                editButton.setOnAction(e -> onEdit(getTableView().getItems().get(getIndex())));
                deleteButton.setOnAction(e -> onDelete(getTableView().getItems().get(getIndex())));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : actionBox);
            }
        });
    }

    private void setupFilters() {
        // Configuration des filtres
        typeFilter.setItems(FXCollections.observableArrayList("Tous", "Concert", "Conférence"));
        typeFilter.setValue("Tous");

        searchField.textProperty().addListener((obs, oldVal, newVal) -> filterEvents());
        typeFilter.valueProperty().addListener((obs, oldVal, newVal) -> filterEvents());
        dateFilter.valueProperty().addListener((obs, oldVal, newVal) -> filterEvents());
    }

    private void loadEvents() {
        ObservableList<Evenement> events = FXCollections.observableArrayList(
            gestionEvenements.getEvenements().values()
        );
        filteredEvents = new FilteredList<>(events);
        eventTable.setItems(filteredEvents);
    }

    private void filterEvents() {
        filteredEvents.setPredicate(event -> {
            boolean matchesSearch = searchField.getText().isEmpty() || 
                event.getNom().toLowerCase().contains(searchField.getText().toLowerCase());
            
            boolean matchesType = typeFilter.getValue().equals("Tous") || 
                event.getClass().getSimpleName().equals(typeFilter.getValue());
            
            boolean matchesDate = dateFilter.getValue() == null || 
                event.getDate().toLocalDate().equals(dateFilter.getValue());
            
            return matchesSearch && matchesType && matchesDate;
        });
        
        setupPagination();
    }

    private void setupPagination() {
        int pageCount = (filteredEvents.size() + ITEMS_PER_PAGE - 1) / ITEMS_PER_PAGE;
        pagination.setPageCount(pageCount);
        pagination.setCurrentPageIndex(0);
        pagination.setPageFactory(this::createPage);
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * ITEMS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ITEMS_PER_PAGE, filteredEvents.size());
        eventTable.setItems(FXCollections.observableArrayList(
            filteredEvents.subList(fromIndex, toIndex)));
        return eventTable;
    }

    @FXML
    public void onCreateEvent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EventDetailView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Nouvel événement");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            
            // Recharge les événements après création
            loadEvents();
        } catch (IOException e) {
            showError("Erreur lors de la création", e);
        }
    }

    @FXML
    public void resetFilters() {
        searchField.clear();
        typeFilter.setValue("Tous");
        dateFilter.setValue(null);
    }

    private void onEdit(Evenement event) {
        // Ouvrir la fenêtre d'édition d'événement
    }

    private void onDelete(Evenement event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer l'événement");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cet événement ?");
        alert.setContentText("Cette action est irréversible.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                gestionEvenements.supprimerEvenement(event);
                loadEvents();
            }
        });
    }
    
    private void showError(String message, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(message);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}