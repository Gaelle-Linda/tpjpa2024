
 # PROJET DE GESTION DE TICKETS D'UN CONCERT

**Lien gitlab:** [https://github.com/Gaelle-Linda/tpjpa2024.git]

## ETUDIANTES

- MAPAGAING LINDA
- JINADU AMDALAT

## ENCADRANT TP

- LE ROCH Adrien

## GROUPE TP

- B

## MASTER 1 MIAGE - Méthodes Informatiques Appliquées à la Gestion des Entreprises

- 2024-2025
 
## Modèle conceptuel de notre application 

![Diagramme de classe](diagramme_de_classe.jpg)


## les parties dejà réalisées

- TP2-4 JPA: Tout a été fait, de la création des classes à la mise à jour de la classe JPA test. nous arrivons à accéder à la base de données et faire les manipulations.

- TP5 partie servlets : tout fonctionne sauf la qustion 5

pour Partie 2 JaxRS et OpenAPI: toute la question 6 a été faite.
Les classes DAO sont implementées 
les méthodes GET et POST fonctionnent
il y'a les classes concert et ressource qui ne sont pas implementées, on doit faire une classe DTO pour la classe Artiste.

# les parties qui ne fonctionne pas

- niveau question 6 TP2: Toutes les classes Dao sont implémentées ainsi que la classe ArtisteRessource et testée via postman, sauf que pour que la méthode GET fonctionne, il faut  commenter:  private List<Concert> concerts et ses dépendances; dans la classe Artiste sinon il y'a un problème de n+1 select qui s'engendre

# Comment démarrer vos projets (script pour lancer la base de données, ...)
- pour le TP 2: Des servlets aux API Rest documentées avec OpenAPI, il faut se positionner sur la branche  apiRestJaxRS
lancer la BD: .\run-hsqldb-server.bat 
afficher la BD: .\show-hsqldb.bat    
Il faut lancer cette classe qui représente le serveur package rest, classe "RestServer"

# Quelles sont les prochaines tâches à faire
- Création des DTO pour la classe Artiste 
- Création de la couche de service pour votre application.

