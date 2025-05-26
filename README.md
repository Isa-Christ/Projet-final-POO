# Projet-final-POO

### Auteur 
MAGNE Isabelle Christ 
3GI

## 1. Présentation du projet

Ce projet est une application de gestion d’événements développée en Java avec JavaFX pour l’interface graphique. Il permet la création, la modification, la suppression et la consultation d’événements (concerts, conférences), la gestion des participants et des organisateurs, ainsi que la notification en temps réel des utilisateurs.

---

## 2. Modélisation du projet

### 2.1 Diagramme de classes (extrait)

- **Evenement** (abstraite)
  - Attributs : id, nom, date, lieu, capacitéMax, participants
  - Méthodes : ajouterParticipant, annuler, afficherDetails
- **Concert** (hérite de Evenement)
  - Attributs : artiste, genreMusical
- **Conference** (hérite de Evenement)
  - Attributs : thème, intervenants
- **Participant**
  - Attributs : id, nom, email
- **Organisateur** (hérite de Participant)
  - Attributs : evenementsOrganises
- **GestionEvenements** (singleton/service)
  - Attributs : Map<String, Evenement>
  - Méthodes : ajouter, supprimer, rechercher, sauvegarder, charger
- **JsonUtil**
  - Méthodes statiques pour la sérialisation/désérialisation JSON

### 2.2 Relations principales

- Un **Evenement** possède plusieurs **Participants**
- Un **Organisateur** gère plusieurs **Evenements**
- **GestionEvenements** centralise la gestion et la persistance des événements

---

## 3. Choix des design patterns

- **Singleton** : utilisé pour la classe `GestionEvenements` afin d’assurer une instance unique du service de gestion dans toute l’application.
- **Observer** : utilisé pour le système de notifications en temps réel (les participants sont observateurs des événements).
- **Factory/Polymorphisme** : la création d’événements polymorphes (Concert, Conference) est facilitée par l’utilisation de l’héritage et des annotations Jackson pour la sérialisation.
- **MVC (Model-View-Controller)** : séparation stricte entre la logique métier (model), l’interface graphique (view FXML) et les contrôleurs JavaFX.

---

## 4. Technologies utilisées

- **Java 17+**
- **JavaFX** : pour l’interface graphique moderne et responsive
- **Jackson** : pour la sérialisation/désérialisation JSON des objets métier
- **JUnit 5** : pour les tests unitaires
- **Maven** : gestion des dépendances et du cycle de vie du projet
- **Ikonli** : pour les icônes dans l’interface utilisateur

---

## 5. Fonctionnalités principales

- Tableau de bord synthétique (statistiques, notifications)
- Gestion CRUD des événements (concerts, conférences)
- Gestion des participants et organisateurs
- Notifications en temps réel (pattern Observer)
- Recherche, filtres et pagination sur les listes
- Persistance des données en JSON
- Interface responsive et ergonomique

---

## 6. Lancement du projet

```sh
mvn clean install
mvn javafx:run
```
ou lancer la classe `App.java` depuis votre IDE.

---

## 7. Structure du projet

```
src/
  main/
    java/
      com/projet_final/
        App.java
        controller/
        model/
        service/
        util/
    resources/
      view/
      css/
  test/
    java/
      com/projet_final/
        ...
```

---

## 8. Limites et perspectives

- Ajout d’une authentification utilisateur
- Export PDF/CSV des listes
- Amélioration de la gestion des notifications (WebSocket, etc.)
- Déploiement web (Spring Boot + REST)

---

