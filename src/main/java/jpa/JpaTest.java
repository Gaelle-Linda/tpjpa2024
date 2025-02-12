package jpa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import models.Client;
import models.Organisateur;
import models.Artiste;
import models.Concert;
import models.Salle;
import models.Ticket;

public class JpaTest {

	private EntityManager manager; // Instance de EntityManager pour gérer les entités

	// Constructeur pour initialiser le gestionnaire d'entités
	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	public static void main(String[] args) {
		// Création de l'EntityManagerFactory et de l'EntityManager pour interagir avec la base de données
		EntityManagerFactory factory =
				Persistence.createEntityManagerFactory("dev"); // Crée une usine d'EntityManager avec la configuration "dev"
		EntityManager manager = factory.createEntityManager(); // Crée un EntityManager
		JpaTest test = new JpaTest(manager); // Crée une instance de JpaTest

		// Démarrage de la transaction pour effectuer les opérations sur la base de données
		EntityTransaction tx = manager.getTransaction();
		tx.begin(); // Démarre la transaction
		try {
			test.createEntities(); // Crée les entités si la base de données est vide
		} catch (Exception e) {
			e.printStackTrace(); // Capture et affiche les erreurs éventuelles
		}
		tx.commit(); // Commit (valide) la transaction après les modifications

		// Liste les entités sauvegardées dans la base de données
		test.listEntities();

		manager.close(); // Ferme l'EntityManager après la fin des opérations
		System.out.println(".. done"); // Affiche un message pour indiquer que l'exécution est terminée
	}

	// Méthode pour créer et persister les entités si la base de données est vide
	private void createEntities() {
		// Vérifie si des entités existent déjà dans la base de données
		int numOfClients = manager.createQuery("Select a From Client a", Client.class).getResultList().size();
		int numOfOrganisateurs = manager.createQuery("Select a From Organisateur a", Organisateur.class).getResultList().size();
		int numOfArtistes = manager.createQuery("Select a From Artiste a", Artiste.class).getResultList().size();
		int numOfConcerts = manager.createQuery("Select a From Concert a", Concert.class).getResultList().size();

		// Si aucune entité n'existe, on procède à leur création
		if (numOfClients == 0 && numOfOrganisateurs == 0 && numOfArtistes == 0 && numOfConcerts == 0) {
			// Création de clients
			Client client1 = new Client("Dupont", "Jean", "jean.dupont@example.com");
			Client client2 = new Client("Martin", "Claire", "claire.martin@example.com");
			manager.persist(client1); // Persiste le client 1
			manager.persist(client2); // Persiste le client 2

			// Création d'un organisateur
			Organisateur organisateur = new Organisateur("Leclerc", "Pierre", "pierre.leclerc@example.com");
			manager.persist(organisateur); // Persiste l'organisateur

			// Création d'artistes
			Artiste artiste1 = new Artiste("Benoît", "Rock");
			Artiste artiste2 = new Artiste("Marie", "Jazz");
			manager.persist(artiste1); // Persiste l'artiste 1
			manager.persist(artiste2); // Persiste l'artiste 2

			// Création d'une salle
			Salle salle = new Salle("Salle de Concert", 200);
			manager.persist(salle); // Persiste la salle

			// Création d'un concert
			Concert concert = new Concert("Concert Rock", "Rock", LocalDate.of(2025, 6, 15), LocalTime.of(20, 0), LocalTime.of(22, 0), 200);
			concert.setOrganisateur(organisateur); // Associe l'organisateur au concert
			concert.setSalle(salle); // Associe la salle au concert
			concert.setArtistes(List.of(artiste1, artiste2)); // Associe les artistes au concert
			manager.persist(concert); // Persiste le concert

			// Création de tickets
			Ticket ticket1 = new Ticket("Ticket VIP");
			ticket1.setClient(client1); // Associe le client 1 au ticket VIP
			ticket1.setConcert(concert); // Associe le concert au ticket VIP
			Ticket ticket2 = new Ticket("Ticket Normal");
			ticket2.setClient(client2); // Associe le client 2 au ticket Normal
			ticket2.setConcert(concert); // Associe le concert au ticket Normal
			manager.persist(ticket1); // Persiste le ticket VIP
			manager.persist(ticket2); // Persiste le ticket Normal
		}
	}

	// Méthode pour lister les entités persistees dans la base de données
	private void listEntities() {
		// Récupère et affiche les clients
		List<Client> clients = manager.createQuery("Select a From Client a", Client.class).getResultList();
		System.out.println("Nombre de clients : " + clients.size());
		for (Client client : clients) {
			System.out.println("Client: " + client.getNom() + " " + client.getPrenom());
		}

		// Récupère et affiche les organisateurs
		List<Organisateur> organisateurs = manager.createQuery("Select a From Organisateur a", Organisateur.class).getResultList();
		System.out.println("Nombre d'organisateurs : " + organisateurs.size());
		for (Organisateur organisateur : organisateurs) {
			System.out.println("Organisateur: " + organisateur.getNom() + " " + organisateur.getPrenom());
		}

		// Récupère et affiche les artistes
		List<Artiste> artistes = manager.createQuery("Select a From Artiste a", Artiste.class).getResultList();
		System.out.println("Nombre d'artistes : " + artistes.size());
		for (Artiste artiste : artistes) {
			System.out.println("Artiste: " + artiste.getNomArtistique());
		}

		// Récupère et affiche les concerts
		List<Concert> concerts = manager.createQuery("Select a From Concert a", Concert.class).getResultList();
		System.out.println("Nombre de concerts : " + concerts.size());
		for (Concert concert : concerts) {
			System.out.println("Concert: " + concert.getDescription() + " (" + concert.getDate() + ")");
		}

		// Récupère et affiche les tickets
		List<Ticket> tickets = manager.createQuery("Select a From Ticket a", Ticket.class).getResultList();
		System.out.println("Nombre de tickets : " + tickets.size());
		for (Ticket ticket : tickets) {
			System.out.println("Ticket: " + ticket.getDescription());
		}
	}
}
